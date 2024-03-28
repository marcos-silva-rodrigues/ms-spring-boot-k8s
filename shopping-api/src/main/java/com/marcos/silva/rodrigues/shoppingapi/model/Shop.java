package com.marcos.silva.rodrigues.shoppingapi.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

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


}
