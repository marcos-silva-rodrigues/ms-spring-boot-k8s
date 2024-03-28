package com.marcos.silva.rodrigues.productapi;

public class DtoConverter {

  public static CategoryDto convert(Category category) {
    CategoryDto categoryDto = new CategoryDto();
    categoryDto.setId(category.getId());
    categoryDto.setNome(category.getNome());

    return categoryDto;
  }

  public static ProductDto convert(Product product) {
    ProductDto dto = new ProductDto();

    dto.setNome(product.getNome());
    dto.setDescricao(product.getDescricao());
    dto.setProductIdentifier(product.getProductIdentifier());
    dto.setPreco(product.getPreco());

    if (product.getCategory() != null) {
      dto.setCategoryDto(
              DtoConverter.convert(product.getCategory()));

    }

    return dto;
  }
}
