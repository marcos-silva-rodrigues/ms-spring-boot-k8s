package com.marcos.silva.rodrigues.shoppingapi;

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
public class ItemDto {

  @NotBlank
  private String productIdentifier;

  @NotNull
  private Float price;

  public static ItemDto convert(Item item) {
    ItemDto dto = new ItemDto();
    dto.setPrice(item.getPrice());
    dto.setProductIdentifier(item.getProductIdentifier());
    return dto;
  }

}
