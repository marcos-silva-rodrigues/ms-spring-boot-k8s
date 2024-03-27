package com.marcos.silva.rodrigues.productapi;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "product")
public class Product {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String nome;
  private Float preco;
  private String descricao;
  private String productIdentifier;

  @ManyToOne
  @JoinColumn(name = "category_id")
  private Category category;

  public static Product convert(ProductDto dto) {
    Product product = new Product();

    product.setNome(dto.getNome());
    product.setDescricao(dto.getDescricao());
    product.setProductIdentifier(dto.getProductIdentifier());
    product.setPreco(dto.getPreco());

    if (dto.getCategoryDto() != null) {
      product.setCategory(
              Category.convert(dto.getCategoryDto()));

    }

    return product;
  }
}
