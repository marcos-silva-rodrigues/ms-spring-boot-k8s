package com.marcos.silva.rodrigues.shoppingapi.service;

import com.marcos.silva.rodrigues.dto.ItemDto;
import com.marcos.silva.rodrigues.dto.ProductDto;
import com.marcos.silva.rodrigues.dto.ShopDto;
import com.marcos.silva.rodrigues.dto.UserDto;
import com.marcos.silva.rodrigues.shoppingapi.repository.ShopRepository;
import com.marcos.silva.rodrigues.shoppingapi.utils.DtoConverter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ShopServiceTest {

  @InjectMocks
  private ShopService shopService;

  @Mock
  private UserService userService;

  @Mock
  private ProductService productService;

  @Mock
  private ShopRepository shopRepository;

  @Test
  public void testSaveShop() {
    ItemDto itemDto = new ItemDto();
    itemDto.setProductIdentifier("123");
    itemDto.setPrice(100F);


    ShopDto shopDto = new ShopDto();
    shopDto.setUserIdentifier("123");
    shopDto.setItems(new ArrayList<>());
    shopDto.getItems().add(itemDto);
    shopDto.setTotal(100F);

    ProductDto productDto= new ProductDto();
    productDto.setProductIdentifier("123");
    productDto.setPreco(100F);

    Mockito.when(userService.getUserByCpfAndKey("123", "123"))
            .thenReturn(new UserDto());
    Mockito.when(productService.getProductByIdentifier("123"))
            .thenReturn(productDto);
    Mockito.when(shopRepository.save(Mockito.any()))
            .thenReturn(DtoConverter.convert(shopDto));

    shopDto = shopService.save(shopDto, "123");
    Assertions.assertEquals(100F, shopDto.getTotal());
    Assertions.assertEquals(1, shopDto.getItems().size());

    Mockito.verify(shopRepository, Mockito.times(1))
            .save(Mockito.any());
  }

}