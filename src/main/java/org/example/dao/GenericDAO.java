package org.example.dao;

import java.util.List;

public interface GenericDAO<Type> {

  Type insert(Type t);

  Type update(Type t);

  boolean delete(int id);

  Type getById(int id);

  List<Type> getAll();
}
