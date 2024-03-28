package com.marcos.silva.rodrigues.shoppingapi;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ShopDto {

  @NotBlank
  private String userIdentifier;

  @NotNull
  private Float total;

  @NotNull
  private LocalDateTime date;

  @NotNull
  private List<ItemDto> items;

  public static ShopDto convert(Shop shop) {
    ShopDto dto = new ShopDto();
    dto.setUserIdentifier(shop.getUserIdentifier());
    dto.setTotal(shop.getTotal());
    dto.setDate(shop.getDate());
    dto.setItems(shop.getItems().stream().map(ItemDto::convert).collect(Collectors.toList()));

    return dto;
  }
}
