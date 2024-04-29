package com.cromoteca.samples.endpoints;

import com.vaadin.flow.server.auth.AnonymousAllowed;
import com.vaadin.hilla.Endpoint;
import com.vaadin.hilla.Loader;
import com.vaadin.hilla.exception.EndpointException;
import java.util.List;

@Endpoint
@AnonymousAllowed
public class LoadersEndpoint {

  public record User(long id, String name) {}

  public static final List<User> userList =
      List.of(new User(1, "John"), new User(2, "Jane"), new User(3, "Alice"));

  public static class UserNotFoundException extends EndpointException {

    public UserNotFoundException(String message) {
      super(message);
    }
  }

  @Loader
  public User getUser(long id) throws UserNotFoundException {
    return userList.stream()
        .filter(user -> user.id() == id)
        .findFirst()
        .orElseThrow(() -> new UserNotFoundException("User with id " + id + " not found"));
  }

  public List<User> getUsers() {
    return userList;
  }
}
