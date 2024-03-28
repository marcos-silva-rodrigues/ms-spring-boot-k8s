package com.marcos.silva.rodrigues.shoppingapi;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity(name = "shop")
public class Shop {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String userIdentifier;
  private Float total;
  private LocalDateTime date;

  @ElementCollection(fetch = FetchType.EAGER)
  @CollectionTable(name = "item", joinColumns = @JoinColumn(name = "shop_id"))
  private List<Item> items;

  public static Shop convert(ShopDto dto) {
    Shop shop = new Shop();
    shop.setUserIdentifier(dto.getUserIdentifier());
    shop.setTotal(dto.getTotal());
    shop.setDate(dto.getDate());
    shop.setItems(dto
            .getItems()
            .stream()
            .map(Item::convert)
            .collect(Collectors.toList()));

    return shop;
  }
}
