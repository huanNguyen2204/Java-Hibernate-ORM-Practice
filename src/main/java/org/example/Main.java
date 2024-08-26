package org.example;

import org.example.dao.GenericDAO;
import org.example.dao.impl.CategoryDAOImpl;
import org.example.dao.impl.ProductDAOImpl;
import org.example.dto.CategoryTotalProductDTO;
import org.example.model.Category;
import org.example.model.Product;
import org.example.util.HibernateUtils;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class Main {

  public static void main(String[] args) {

    //    // NOTE: Run only 1 times and comments later
//    // 1. Create 2 class of DAO
//    GenericDAO<Category> categoryDAO = new CategoryDAOImpl();
//    GenericDAO<Product> productDAO = new ProductDAOImpl();
//
//    // 2. Create Category and Product Object (3 for category, 6 items for product)
//    // Category
//    Category category1 = new Category();
//    category1.setId(1L);
//    category1.setCategoryName("Electrical");
//    category1.setCategoryDescription("Category desc 1");
//
//    Category category2 = new Category();
//    category2.setId(2L);
//    category2.setCategoryName("Furniture");
//    category2.setCategoryDescription("Category desc 2");
//
//    Category category3 = new Category();
//    category3.setId(3L);
//    category3.setCategoryName("Clothes");
//    category3.setCategoryDescription("Category desc 3");
//
//    // Product
//    Product product1 = new Product();
//    product1.setId(1L);
//    product1.setProductName("IPhone 15 Pro Max");
//    product1.setProductPrice(2000.0);
//    product1.setProductDescription("Product desc 1");
//    product1.setCategory(category1);
//
//    Product product2 = new Product();
//    product2.setId(2L);
//    product2.setProductName("Macbook 16 inch M1");
//    product2.setProductPrice(3500.0);
//    product2.setProductDescription("Product desc 2");
//    product2.setCategory(category1);
//
//    Product product3 = new Product();
//    product3.setId(3L);
//    product3.setProductName("Red Sexy Dressed");
//    product3.setProductPrice(1850.0);
//    product3.setProductDescription("Product desc 3");
//    product3.setCategory(category3);
//
//    Product product4 = new Product();
//    product4.setId(4L);
//    product4.setProductName("Fake IPad of China");
//    product4.setProductPrice(1.0);
//    product4.setProductDescription("Product desc 4");
//    product4.setCategory(category1);
//
//    Product product5 = new Product();
//    product5.setId(5L);
//    product5.setProductName("Black Trouser");
//    product5.setProductPrice(500.0);
//    product5.setProductDescription("Product desc 5");
//    product5.setCategory(category3);
//
//    Product product6 = new Product();
//    product6.setId(6L);
//    product6.setProductName("Lovely horse sofa");
//    product6.setProductPrice(9999.9);
//    product6.setProductDescription("Product desc 6");
//    product6.setCategory(category2);
//
//    // 3. Implements of DAO
//    categoryDAO.insert(category1);
//    categoryDAO.insert(category2);
//    categoryDAO.insert(category3);
//
//    productDAO.insert(product1);
//    productDAO.insert(product2);
//    productDAO.insert(product3);
//    productDAO.insert(product4);
//    productDAO.insert(product5);
//    productDAO.insert(product6);

    // 4. Execute the request
    // - 4.1.
    System.out.println("\nRequest 1:");
    List<Product> result1 = findProductsByKeyword("IP");
    for (Product p : result1)
      System.out.println(p.toString());

    // - 4.2.
    System.out.println("\nRequest 2:");
    List<CategoryTotalProductDTO> result2 = countProductsByCategory();
    for (CategoryTotalProductDTO c : result2)
      System.out.println(c.toString());

    // - 4.3.
    System.out.println("\nRequest 3:");
    List<Product> result3 = findProductsByCategory("Electrical");
    for (Product p : result3)
      System.out.println(p.toString());
  }

  private static List<Product> findProductsByKeyword(String keyword) {
    try (Session session = HibernateUtils.getSessionFactory().openSession()) {
      String sql = """
          SELECT p
          FROM Product p
          WHERE p.productName LIKE :keyword
        """;
      Query query = session.createQuery(sql, Product.class);
      query.setParameter("keyword", "%" + keyword + "%");
      List<Product> productList = query.getResultList();
      return productList;
    } catch (Exception ex) {
      ex.printStackTrace();
      return null;
    }
  }

  private static List<CategoryTotalProductDTO> countProductsByCategory() {
    try (Session session = HibernateUtils.getSessionFactory().openSession()) {
      String sql = """
          SELECT NEW org.example.dto.CategoryTotalProductDTO(c.categoryName, COUNT(p.id))
          FROM Category c
        	JOIN c.products p
          GROUP BY c.id
        """;
      Query query = session.createQuery(sql, CategoryTotalProductDTO.class);
      List<CategoryTotalProductDTO> categoryTotalProductDTOList = query.getResultList();
      return categoryTotalProductDTOList;
    } catch (Exception ex) {
      ex.printStackTrace();
      return null;
    }
  }

  private static List<Product> findProductsByCategory(String categoryName) {
    try (Session session = HibernateUtils.getSessionFactory().openSession()) {
      String sql = """
          SELECT p
          FROM Product p
          WHERE p.category = (
            SELECT c
            FROM Category c
            WHERE c.categoryName = :categoryName
          )
        """;
      Query query = session.createQuery(sql, Product.class);
      query.setParameter("categoryName", categoryName);
      List<Product> productList = query.getResultList();
      return productList;
    } catch (Exception ex) {
      ex.printStackTrace();
      return null;
    }
  }
}