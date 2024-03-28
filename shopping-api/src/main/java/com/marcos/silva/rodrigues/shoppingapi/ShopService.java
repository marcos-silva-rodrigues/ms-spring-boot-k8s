package com.marcos.silva.rodrigues.shoppingapi;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ShopService {

  private final ShopRepository shopRepository;

  public List<ShopDto> getAll() {
    List<Shop> shops = shopRepository.findAll();

    return shops.stream().map(ShopDto::convert).collect(Collectors.toList());
  }

  public List<ShopDto> getByUser(String userIdentifier) {
    List<Shop> shops= shopRepository.findAllByUserIdentifier(userIdentifier);
    return shops.stream().map(ShopDto::convert).collect(Collectors.toList());
  }

  public List<ShopDto> getByDate(LocalDateTime date) {
    List<Shop> shops= shopRepository.findAllByDateGreaterThan(date);
    return shops.stream().map(ShopDto::convert).collect(Collectors.toList());
  }

  public ShopDto findById(Long id) {
    Optional<Shop> optionalShop =  shopRepository.findById(id);
    return optionalShop.map(ShopDto::convert).orElse(null);
  }

  public ShopDto save(ShopDto dto) {
    dto.setTotal(
            dto.getItems()
              .stream()
              .map(ItemDto::getPrice)
              .reduce((float) 0, Float::sum));

    Shop shop = Shop.convert(dto);
    shop.setDate(LocalDateTime.now());

    shop = shopRepository.save(shop);
    return ShopDto.convert(shop);
  }


}
