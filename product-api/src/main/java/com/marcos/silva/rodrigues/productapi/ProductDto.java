package com.marcos.silva.rodrigues.productapi;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductDto {

  @NotBlank
  private String productIdentifier;

  @NotBlank
  private String nome;

  @NotBlank
  private String descricao;

  @NotNull
  private Float preco;

  @NotNull
  private CategoryDto categoryDto;

  public static ProductDto convert(Product product) {
    ProductDto dto = new ProductDto();

    dto.setNome(product.getNome());
    dto.setDescricao(product.getDescricao());
    dto.setProductIdentifier(product.getProductIdentifier());
    dto.setPreco(product.getPreco());

    if (product.getCategory() != null) {
      dto.setCategoryDto(
              CategoryDto.convert(product.getCategory()));

    }

    return dto;
  }


}
