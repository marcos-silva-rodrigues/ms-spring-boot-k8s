package com.marcos.silva.rodrigues.shoppingapi;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class Item {

  private String productIdentifier;
  private Float price;

  public static Item convert(ItemDto dto) {
    Item item = new Item();
    item.setPrice(dto.getPrice());
    item.setProductIdentifier(dto.getProductIdentifier());
    return item;
  }
}