package com.marcos.silva.rodrigues.shoppingapi;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/shopping")
@RequiredArgsConstructor
public class ShopController {

  private final ShopService shopService;

  @GetMapping
  public List<ShopDto> getAll () {
    return shopService.getAll();
  }

  @GetMapping("/{id}")
  public ShopDto getById (@PathVariable Long id) {
    return shopService.findById(id);
  }

  @GetMapping("/shopByUser/{userIdentifier}")
  public List<ShopDto> getByUser (@PathVariable String userIdentifier) {
    return shopService.getByUser(userIdentifier);
  }

  @GetMapping("/shopByDate")
  public List<ShopDto> getByDate (@RequestBody ShopDto dto) {
    return shopService.getByDate(dto.getDate());
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public ShopDto create (@RequestBody ShopDto dto) {
    return shopService.save(dto);
  }


}
