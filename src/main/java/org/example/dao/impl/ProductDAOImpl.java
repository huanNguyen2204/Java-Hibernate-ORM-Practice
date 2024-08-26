package org.example.dao.impl;

import org.example.dao.GenericDAO;
import org.example.model.Product;
import org.example.util.HibernateUtils;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class ProductDAOImpl implements GenericDAO<Product> {

  @Override
  public Product insert(Product product) {
    Transaction transaction = null;
    try (Session session = HibernateUtils.getSessionFactory().openSession()) {
      transaction = session.beginTransaction();
      session.save(product);
      transaction.commit();
    } catch (Exception ex) {
      ex.printStackTrace();
    }
    return product;
  }

  @Override
  public Product update(Product product) {
    Transaction transaction = null;
    try (Session session = HibernateUtils.getSessionFactory().openSession()) {
      transaction = session.beginTransaction();
      session.update(product);
      transaction.commit();
    } catch (Exception ex) {
      ex.printStackTrace();
    }
    return product;
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
  public Product getById(int id) {
    try (Session session = HibernateUtils.getSessionFactory().openSession()) {
      Product product = session.get(Product.class, id);
      return product;
    } catch (Exception ex) {
      ex.printStackTrace();
      return null;
    }
  }

  @Override
  public List<Product> getAll() {
    try (Session session = HibernateUtils.getSessionFactory().openSession()) {
      String sql = """
          SELECT p
          FROM Product p
        """;
      Query query = session.createQuery(sql, Product.class);
      List<Product> productList = query.getResultList();
      return productList;
    } catch (Exception ex) {
      ex.printStackTrace();
      return null;
    }
  }
}
