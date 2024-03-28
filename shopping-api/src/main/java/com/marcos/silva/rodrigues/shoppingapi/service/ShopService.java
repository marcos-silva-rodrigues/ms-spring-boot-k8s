package com.marcos.silva.rodrigues.shoppingapi.service;

import com.marcos.silva.rodrigues.dto.ItemDto;
import com.marcos.silva.rodrigues.dto.ProductDto;
import com.marcos.silva.rodrigues.dto.ShopDto;
import com.marcos.silva.rodrigues.dto.ShopReportDto;
import com.marcos.silva.rodrigues.shoppingapi.model.Shop;
import com.marcos.silva.rodrigues.shoppingapi.repository.ShopRepository;
import com.marcos.silva.rodrigues.shoppingapi.utils.DtoConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ShopService {

  private final ShopRepository shopRepository;
  private final UserService userService;
  private final ProductService productService;

  public List<ShopDto> getAll() {
    List<Shop> shops = shopRepository.findAll();

    return shops.stream().map(DtoConverter::convert).collect(Collectors.toList());
  }

  public List<ShopDto> getByUser(String userIdentifier) {
    List<Shop> shops= shopRepository.findAllByUserIdentifier(userIdentifier);
    return shops.stream().map(DtoConverter::convert).collect(Collectors.toList());
  }

  public List<ShopDto> getByDate(LocalDateTime date) {
    List<Shop> shops= shopRepository.findAllByDateGreaterThan(date);
    return shops.stream().map(DtoConverter::convert).collect(Collectors.toList());
  }

  public ShopDto findById(Long id) {
    Optional<Shop> optionalShop =  shopRepository.findById(id);
    return optionalShop.map(DtoConverter::convert).orElse(null);
  }

  public ShopDto save(ShopDto dto) {
    if (userService.getUserByCpf(dto.getUserIdentifier()) == null) {
      return null;
    }

    if(!validateProducts(dto.getItems())) {
      return null;
    }

    dto.setTotal(
            dto.getItems()
              .stream()
              .map(ItemDto::getPrice)
              .reduce((float) 0, Float::sum));

    Shop shop = DtoConverter.convert(dto);
    shop.setDate(LocalDateTime.now());

    shop = shopRepository.save(shop);
    return DtoConverter.convert(shop);
  }

  private boolean validateProducts(List<ItemDto> itemDtos) {
    for (ItemDto dto : itemDtos) {
      ProductDto productDto = productService
              .getProductByIdentifier(dto.getProductIdentifier());
      if(productDto == null) {
        return false;
      }

      dto.setPrice(productDto.getPreco());
    }

    return true;
  }


  public List<ShopDto> getShopByFilter(
          LocalDate dataInicio,
          LocalDate dataFim,
          Float valorMinimo
  ) {
    List<Shop> shops = shopRepository.getShoByFilters(dataInicio, dataFim, valorMinimo);

    return shops
            .stream()
            .map(DtoConverter::convert)
            .collect(Collectors.toList());
  }

  public ShopReportDto getReportByDate(
          LocalDate dataInicio,
          LocalDate dataFim
  ) {
    ShopReportDto reportDto = shopRepository.getReportByDate(dataInicio, dataFim);

    return reportDto;
  }


}
