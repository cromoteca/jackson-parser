package com.example.library;

import java.util.List;

public interface CRUD<T> {
  List<T> getAll();

  T saveOrUpdate(T t);

  T remove(T t);

  default void validate(T t) {}
}
