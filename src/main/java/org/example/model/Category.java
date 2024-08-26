package org.example.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "category")
public class Category {

  // Attributes of Entity
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  @NotBlank(message = "The name of category can not be empty.")
  @Size(min = 3, max = 20, message = "The name of category must have length in range [3, 20]")
  @Column(name = "category_name")
  private String categoryName;

  @Column(name = "category_description")
  private String categoryDescription;

  // Relationship
  @OneToMany(mappedBy = "category")
  private Set<Product> products = new HashSet<>();

  @Override
  public String toString() {
    return "Category{" +
      "id=" + id +
      ", categoryName='" + categoryName + '\'' +
      ", categoryDescription='" + categoryDescription + '\'' +
      '}';
  }
}
