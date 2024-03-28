package com.marcos.silva.rodrigues.shoppingapi;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ShopReportDto {

  private Long count;
  private Double total;
  private Double mean;
}
