package com.marcos.silva.rodrigues.productapi;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CategoryDto {

  @NotNull
  private Long id;
  private String nome;

  public static CategoryDto convert(Category category) {
    CategoryDto categoryDto = new CategoryDto();
    categoryDto.setId(category.getId());
    categoryDto.setNome(category.getNome());

    return categoryDto;
  }

}
