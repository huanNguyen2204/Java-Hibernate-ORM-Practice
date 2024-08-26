package org.example.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "product")
public class Product {

  // Attributes of Entity
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  @NotBlank(message = "The name of product can not be empty.")
  @Column(name = "product_name")
  private String productName;

  @NotNull(message = "The price of product can not be empty")
  @Min(value = 0, message = "The price of product must higher than 0")
  @Column(name = "product_price")
  private double productPrice;

  @Column(name = "product_description")
  private String productDescription;

  // Relationship
  @ManyToOne
  @JoinColumn(name = "category_id")
  private Category category;

  @Override
  public String toString() {
    return "Product{" +
      "id=" + id +
      ", productName='" + productName + '\'' +
      ", productPrice=" + productPrice +
      ", productDescription='" + productDescription + '\'' +
      ", category=" + category.getId() +
      '}';
  }
}
