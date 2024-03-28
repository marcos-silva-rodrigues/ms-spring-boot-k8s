package com.marcos.silva.rodrigues;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

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


}
