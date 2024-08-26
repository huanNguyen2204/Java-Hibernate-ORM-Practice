package org.example.util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtils {

  private static SessionFactory sessionFactory = null;

  static {
    Configuration cfg = new Configuration();
    cfg.configure();
    if (sessionFactory == null) {
      sessionFactory = cfg.buildSessionFactory();
    }
  }

  public static SessionFactory getSessionFactory() {
    return sessionFactory;
  }
}
