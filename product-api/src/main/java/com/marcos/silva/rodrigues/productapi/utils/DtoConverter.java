package com.marcos.silva.rodrigues.productapi.utils;

import com.marcos.silva.rodrigues.dto.CategoryDto;
import com.marcos.silva.rodrigues.dto.ProductDto;
import com.marcos.silva.rodrigues.productapi.model.Category;
import com.marcos.silva.rodrigues.productapi.model.Product;

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

  public static Category convert(CategoryDto dto) {
    Category category = new Category();
    category.setId(dto.getId());
    category.setNome(dto.getNome());

    return category;
  }

  public static Product convert(ProductDto dto) {
    Product product = new Product();

    product.setNome(dto.getNome());
    product.setDescricao(dto.getDescricao());
    product.setProductIdentifier(dto.getProductIdentifier());
    product.setPreco(dto.getPreco());

    if (dto.getCategoryDto() != null) {
      product.setCategory(
              DtoConverter.convert(dto.getCategoryDto()));

    }

    return product;
  }
}
