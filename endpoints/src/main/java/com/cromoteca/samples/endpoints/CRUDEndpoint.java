package com.cromoteca.samples.endpoints;

import com.example.library.CRUD;
import com.example.library.MyLibraryType;
import com.vaadin.hilla.Endpoint;
import java.util.List;

@Endpoint
public class CRUDEndpoint implements CRUD<MyLibraryType> {
  @Override
  public List<MyLibraryType> getAll() {
    return null;
  }

  @Override
  public MyLibraryType saveOrUpdate(MyLibraryType t) {
    return t;
  }

  @Override
  public MyLibraryType remove(MyLibraryType t) {
    return t;
  }

  public MyLibraryType doSomethingWith(MyLibraryType t) {
    return t;
  }
}
