package com.marcos.silva.rodrigues;

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




}
