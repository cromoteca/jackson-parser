package com.example.application.endpoints;

import com.cromoteca.bfts.CommandLine;
import com.cromoteca.bfts.client.ClientActivities;
import com.cromoteca.bfts.client.ClientScheduler;
import com.cromoteca.bfts.client.Configuration;
import com.cromoteca.bfts.client.Filesystem;
import com.cromoteca.bfts.cryptography.Cryptographer;
import com.cromoteca.bfts.model.Pair;
import com.cromoteca.bfts.model.Source;
import com.cromoteca.bfts.model.Stats;
import com.cromoteca.bfts.model.StorageConfiguration;
import com.cromoteca.bfts.storage.EncryptedStorages;
import com.cromoteca.bfts.storage.EncryptionType;
import com.cromoteca.bfts.storage.FileStatus;
import com.cromoteca.bfts.storage.InitializationException;
import com.cromoteca.bfts.storage.LocalStorage;
import com.cromoteca.bfts.storage.RemoteStorage;
import com.cromoteca.bfts.storage.RemoteStorageServer;
import com.cromoteca.bfts.storage.Storage;
import com.cromoteca.bfts.util.Factory;
import com.cromoteca.bfts.util.FilePath;
import com.cromoteca.bfts.util.Util;
import com.vaadin.flow.server.auth.AnonymousAllowed;
import com.vaadin.hilla.Endpoint;
import com.vaadin.hilla.Nullable;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.function.IntConsumer;
import java.util.prefs.Preferences;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;
import org.ocpsoft.prettytime.PrettyTime;

@Endpoint
@AnonymousAllowed
public class ConfigurationEndpoint {

  private static final Pattern HOST_PORT = Pattern.compile("(.+):(\\d+)");
  private static final Factory FACTORY = new Factory();

  private static final Configuration CONFIG =
      new Configuration(Preferences.userNodeForPackage(CommandLine.class));

  private char[] password;

  public void clearConfiguration() {
    CONFIG.remove();
  }

  public void name(String name) {
    if (!Util.validName(name)) {
      throw new IllegalArgumentException("Name is not valid");
    } else {
      CONFIG.setClientName(name);
    }
  }

  public void password(String password) {
    this.password = password.toCharArray();
  }

  public void noPassword() {
    password = null;
  }

  public String init(String name, String path, boolean inMemory) {
    StorageConfiguration storageConfig = new StorageConfiguration();
    LocalStorage.init(FilePath.get(path), inMemory, storageConfig);
    CONFIG.setLocalStoragePath(name, path);
    return String.format("Local storage %s initialized in directory %s", name, path);
  }

  public String publish(String name, int port) throws GeneralSecurityException {
    String path = CONFIG.getLocalStoragePath(name);
    LocalStorage storage = LocalStorage.get(FilePath.get(path));
    StorageConfiguration storageConfig = storage.getStorageConfiguration();

    // The HTTP connection needs a key pair to encrypt exchanged data
    Cryptographer crypto = new Cryptographer(storageConfig.getSalt(), password);
    storage.addKeyPair(crypto.generateKeyPair());
    CONFIG.setLocalStoragePort(name, port);
    return String.format("Storage %s published on port %d", name, port);
  }

  public String connect(String name, String path, EncryptionType encryptionType) {
    CONFIG.setConnectedStoragePath(name, path);
    CONFIG.setConnectedStorageEncryptionType(name, encryptionType);
    return String.format("Prepared connection to storage %s as %s", name, CONFIG.getClientName());
  }

  public String add(String storageName, String name, String path) throws IOException {
    if (!Util.validName(name)) {
      throw new IllegalArgumentException("Source name is not valid");
    } else {
      FilePath directory = FilePath.get(path);

      if (directory.isDirectory()) {
        Storage storage = getStorage(storageName);
        storage.addSource(CONFIG.getClientName(), name, path);
        return String.format("Added source %s to storage %s", name, storageName);
      } else {
        return String.format("%s is not a directory", path);
      }
    }
  }

  public String priority(String storageName, String name, int priority) {
    Storage storage = getStorage(storageName);
    storage.setSourcePriority(CONFIG.getClientName(), name, priority);
    return String.format("Priority for %s set to %d", name, priority);
  }

  public record LocalStorageConfiguration(String name, String path, int port) {}

  public record ConnectedStorageConfiguration(
      String name, String path, EncryptionType encryptionType) {}

  public Pair<List<LocalStorageConfiguration>, List<ConnectedStorageConfiguration>> listStorages() {
    var localStorages =
        Arrays.stream(CONFIG.getLocalStorages())
            .map(
                name ->
                    new LocalStorageConfiguration(
                        name, CONFIG.getLocalStoragePath(name), CONFIG.getLocalStoragePort(name)))
            .toList();
    var connectedStorages =
        Arrays.stream(CONFIG.getConnectedStorages())
            .map(
                name ->
                    new ConnectedStorageConfiguration(
                        name,
                        CONFIG.getConnectedStoragePath(name),
                        CONFIG.getConnectedStorageEncryptionType(name)))
            .toList();
    return new Pair<>(localStorages, connectedStorages);
  }

  public record SourceConfiguration(String name, String path, int priority) {}

  public List<SourceConfiguration> listStorage(String name) {
    Storage storage = getStorage(name);
    return storage.selectSources(CONFIG.getClientName()).stream()
        .map(
            source ->
                new SourceConfiguration(
                    source.getName(), source.getRootPath(), source.getPriority()))
        .toList();
  }

  public void complete(String storageName) {
    Storage storage = getStorage(storageName);
    Filesystem fs = new Filesystem();
    fs.setFilesystemScanSize(Integer.MAX_VALUE);
    ClientActivities ca =
        new ClientActivities(
            CONFIG.getClientName(), fs, storage, storageName, CONFIG.getLongOperationDuration());

    for (Source source : storage.selectSources(CONFIG.getClientName())) {
      doCompleteBackup(ca, source.getName());
    }
  }

  public void completeSource(String storageName, String sourceName) {
    Storage storage = getStorage(storageName);
    Filesystem fs = new Filesystem();
    fs.setFilesystemScanSize(Integer.MAX_VALUE);
    ClientActivities ca =
        new ClientActivities(
            CONFIG.getClientName(), fs, storage, storageName, CONFIG.getLongOperationDuration());
    doCompleteBackup(ca, sourceName);
  }

  private String doCompleteBackup(ClientActivities ca, String sourceName) {
    Source source = ca.selectSource(false, sourceName);

    if (source == null) {
      return String.format("Source %s is not available at the moment", sourceName);
    } else {
      ca.sendFiles(source);
      for (int n = 1; n > 0; n = ca.syncDeletions(source, true).size()) {}
      for (int n = 1; n > 0; n = ca.syncAdditions(source, true).size()) {}
      for (int n = 1; n > 0; n = ca.sendHashes(FileStatus.CURRENT, source.getId())) {}
      for (int n = 1; n > 0; n = ca.uploadChunks(FileStatus.CURRENT, source.getId())) {}
      return String.format("Backup of source %s completed", sourceName);
    }
  }

  public void startAll() {
    start(null);
  }

  public List<String> start(@Nullable String name) {
    // start all HTTP servers, for use by remote clients
    Stream<String> stream = Arrays.stream(CONFIG.getLocalStorages());

    if (name != null) {
      stream = stream.filter(n -> name.equals(n));
    }

    // collect paths and ports
    var serverMessages =
        stream
            .map(n -> new Pair<>(CONFIG.getLocalStoragePath(n), CONFIG.getLocalStoragePort(n)))
            // keep those with a valid port number (port is 0 when not published)
            .filter(storage -> storage.getSecond() > 0)
            .map(
                storage -> {
                  String path = storage.getFirst();
                  Integer port = storage.getSecond();

                  try {
                    LocalStorage localStorage = LocalStorage.get(FilePath.get(path));
                    RemoteStorageServer server = new RemoteStorageServer(localStorage);

                    // start server and keep reference to be able to stop it
                    IntConsumer stop = server.startHTTPServer(port);
                    FACTORY.registerSingleton(IntConsumer.class, port, stop);
                  } catch (InitializationException ex) {
                    return String.format(
                        "Storage server %s not started: %s", path, ex.getMessage());
                  } catch (IOException ex) {
                    throw new RuntimeException(ex);
                  }

                  return String.format("Server started for %s on port %d", path, port);
                })
            .toList();

    Filesystem filesystem = new Filesystem();

    // start all backups
    stream = Arrays.stream(CONFIG.getConnectedStorages());

    if (name != null) {
      stream = stream.filter(n -> name.equals(n));
    }

    var clientMessages =
        stream
            .map(
                n -> {
                  Storage storage = getStorage(n);

                  if (storage == null) {
                    return String.format("Storage server %s is not available", n);
                  } else {
                    // one ClientActivities object for each backup destination
                    ClientActivities ca =
                        new ClientActivities(
                            CONFIG.getClientName(),
                            filesystem,
                            storage,
                            n,
                            CONFIG.getLongOperationDuration());

                    // one scheduler for each backup destination
                    ClientScheduler cs = new ClientScheduler(ca, 5000, 150000);
                    FACTORY.registerSingleton(ClientScheduler.class, n, cs);
                    cs.start();
                    return String.format("Client scheduler started for %s", n);
                  }
                })
            .toList();

    return Stream.concat(serverMessages.stream(), clientMessages.stream()).toList();
  }

  public void stopAll() {
    stop(null);
  }

  public void stop(@Nullable String name) {
    System.out.print("Stopping running backup... ");

    Stream<String> stream = Arrays.stream(CONFIG.getConnectedStorages());

    if (name != null) {
      stream = stream.filter(n -> name.equals(n));
    }

    // stopping more backups at the same time (not optimal since parallel is
    // based on the number of CPUs)
    stream
        .parallel()
        .forEach(
            n -> {
              ClientScheduler cs = FACTORY.obtain(ClientScheduler.class, n);

              if (cs != null) {
                cs.stop();
                FACTORY.unregister(ClientScheduler.class, n);
              }
            });

    System.out.print("and local storage... ");

    stream = Arrays.stream(CONFIG.getLocalStorages());

    if (name != null) {
      stream = stream.filter(n -> name.equals(n));
    }

    // close more storages at the same time
    stream
        .parallel()
        .forEach(
            n -> {
              int port = CONFIG.getLocalStoragePort(n);

              // stop the related HTTP server if the storage is published
              if (port > 0) {
                IntConsumer stop = FACTORY.obtain(IntConsumer.class, port);

                if (stop != null) {
                  stop.accept(10);
                  FACTORY.unregister(IntConsumer.class, port);
                }
              }

              FilePath path = FilePath.get(CONFIG.getLocalStoragePath(n));

              try {
                LocalStorage.get(path).close();
              } catch (InitializationException ex) {
                System.out.format("Storage server %s not stopped: %s\n", path, ex.getMessage());
              }
            });

    System.out.println("done");
  }

  public List<String> stats(String name) {
    Storage storage = getStorage(name);
    PrettyTime pt = new PrettyTime(Locale.UK);

    return storage.getDetailedClientStats(CONFIG.getClientName()).entrySet().stream()
        .map(
            entry -> {
              Stats stats = entry.getValue();
              long time = stats.getLastUpdated();
              return String.format(
                  "%s:\n    Last updated: %s\n    Files: %d\n"
                      + "    Files without hash: %d\n    Missing file chunks: %d\n",
                  entry.getKey(),
                  time == 0 ? "never" : pt.format(new Date(time)),
                  stats.getFiles(),
                  stats.getFilesWithoutHash(),
                  stats.getMissingChunks());
            })
        .toList();
  }

  public void fast() {
    setFast(true);
  }

  public void nice() {
    setFast(false);
  }

  private void setFast(boolean fast) {
    Arrays.stream(CONFIG.getConnectedStorages())
        .forEach(
            name -> {
              ClientScheduler cs = FACTORY.obtain(ClientScheduler.class, name);

              if (cs != null) {
                cs.setFast(fast);
              }
            });
  }

  private Storage getStorage(String storageName) {
    String path = CONFIG.getConnectedStoragePath(storageName);
    EncryptionType encryptionType = CONFIG.getConnectedStorageEncryptionType(storageName);
    Storage storage = null;
    Pair<String, Integer> split = splitHostPort(path);

    if (split == null) {
      // local storage
      try {
        storage = LocalStorage.get(FilePath.get(path));
        System.out.format("Connected to local storage %s\n", path);
      } catch (InitializationException ex) {
        System.out.format("Storage server %s not started: %s\n", path, ex.getMessage());
      }
    } else {
      // remote storage
      String host = split.getFirst();
      int port = split.getSecond();
      storage = RemoteStorage.create(host, port, password);
      System.out.format("Connected to remote storage %s\n", path);
    }

    if (storage != null) {
      switch (encryptionType) {
        case DATA:
          storage = EncryptedStorages.getEncryptedStorage(storage, password, false);
          System.out.format("Using data encryption on storage %s\n", path);
          break;
        case FULL:
          storage = EncryptedStorages.getEncryptedStorage(storage, password, true);
          System.out.format("Using full encryption on storage %s\n", path);
          break;
        case NONE:
          // leave storage unencrypted
      }
    }

    return storage;
  }

  private static Pair<String, Integer> splitHostPort(String s) {
    Matcher m = HOST_PORT.matcher(s);

    if (m.matches()) {
      return new Pair<>(m.group(1), Integer.valueOf(m.group(2)));
    } else {
      return null;
    }
  }
}
