package com.cromoteca.samples.endpoints;

import com.cromoteca.samples.annotations.Nullable;
import dev.hilla.Endpoint;
import java.math.BigInteger;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Endpoint
public class ReviverEndpoint {

  public Date date(Date arg) {
    return arg;
  }

  @Nullable
  public Date nullableDate(@Nullable Date arg) {
    return arg;
  }

  public BigInteger bigInteger(BigInteger arg) {
    return arg;
  }

  public Map<Integer, List<Date>> map(Map<Integer, List<Date>> arg) {
    return arg;
  }

  public Level2 reviveProperties(Level2 arg) {
    return arg;
  }

  public record Level1(String name, Date date) {}

  public record Level2(int id, @Nullable Level1 info) {}
}
