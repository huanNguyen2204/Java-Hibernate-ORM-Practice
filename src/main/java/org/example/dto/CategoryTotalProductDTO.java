package org.example.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CategoryTotalProductDTO {

  private String categoryName;
  private Long totalProducts;

  @Override
  public String toString() {
    return "CategoryTotalProductDTO{" +
      "categoryName='" + categoryName + '\'' +
      ", totalProducts=" + totalProducts +
      '}';
  }
}
