package com.marcos.silva.rodrigues.shoppingapi;

import com.marcos.silva.rodrigues.ItemDto;
import com.marcos.silva.rodrigues.ShopDto;

import java.util.stream.Collectors;

public class DtoConverter {

  public static ItemDto convert(Item item) {
    ItemDto dto = new ItemDto();
    dto.setPrice(item.getPrice());
    dto.setProductIdentifier(item.getProductIdentifier());
    return dto;
  }

  public static Item convert(ItemDto dto) {
    Item item = new Item();
    item.setPrice(dto.getPrice());
    item.setProductIdentifier(dto.getProductIdentifier());
    return item;
  }

  public static ShopDto convert(Shop shop) {
    ShopDto dto = new ShopDto();
    dto.setUserIdentifier(shop.getUserIdentifier());
    dto.setTotal(shop.getTotal());
    dto.setDate(shop.getDate());
    dto.setItems(shop.getItems().stream().map(DtoConverter::convert).collect(Collectors.toList()));

    return dto;
  }

  public static Shop convert(ShopDto dto) {
    Shop shop = new Shop();
    shop.setUserIdentifier(dto.getUserIdentifier());
    shop.setTotal(dto.getTotal());
    shop.setDate(dto.getDate());
    shop.setItems(dto
            .getItems()
            .stream()
            .map(DtoConverter::convert)
            .collect(Collectors.toList()));

    return shop;
  }
}
