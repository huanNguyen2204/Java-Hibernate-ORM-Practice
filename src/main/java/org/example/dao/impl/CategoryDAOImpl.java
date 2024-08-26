package org.example.dao.impl;

import org.example.dao.GenericDAO;
import org.example.model.Category;
import org.example.util.HibernateUtils;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class CategoryDAOImpl implements GenericDAO<Category> {

  @Override
  public Category insert(Category category) {
    Transaction transaction = null;
    try (Session session = HibernateUtils.getSessionFactory().openSession()) {
      transaction = session.beginTransaction();
      session.save(category);
      transaction.commit();
    } catch (Exception ex) {
      ex.printStackTrace();
    }
    return category;
  }

  @Override
  public Category update(Category category) {
    Transaction transaction = null;
    try (Session session = HibernateUtils.getSessionFactory().openSession()) {
      transaction = session.beginTransaction();
      session.update(category);
      transaction.commit();
    } catch (Exception ex) {
      ex.printStackTrace();
    }
    return category;
  }

  @Override
  public boolean delete(int id) {
    Transaction transaction = null;
    try (Session session = HibernateUtils.getSessionFactory().openSession()) {
      transaction = session.beginTransaction();
      session.delete(id);
      transaction.commit();
      return true;
    } catch (Exception ex) {
      ex.printStackTrace();
    }
    return false;
  }

  @Override
  public Category getById(int id) {
    try (Session session = HibernateUtils.getSessionFactory().openSession()) {
      Category category = session.get(Category.class, id);
      return category;
    } catch (Exception ex) {
      ex.printStackTrace();
      return null;
    }
  }

  @Override
  public List<Category> getAll() {
    try (Session session = HibernateUtils.getSessionFactory().openSession()) {
      String sql = """
          SELECT c  
          FROM Category c
        """;
      Query query = session.createQuery(sql, Category.class);
      List<Category> categoryList = query.getResultList();
      return categoryList;
    } catch (Exception ex) {
      ex.printStackTrace();
      return null;
    }
  }
}
