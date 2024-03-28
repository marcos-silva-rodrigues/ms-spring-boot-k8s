package com.marcos.silva.rodrigues.shoppingapi.controller;

import com.marcos.silva.rodrigues.dto.ShopDto;
import com.marcos.silva.rodrigues.dto.ShopReportDto;
import com.marcos.silva.rodrigues.shoppingapi.service.ShopService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
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
  public ShopDto create (@RequestBody ShopDto dto, @RequestHeader(name = "key", required = true) String key) {
    return shopService.save(dto, key);
  }

  @GetMapping("/search")
  public List<ShopDto> getShopByFilter(
          @RequestParam(name = "dataInicio", required = true)
          @DateTimeFormat(pattern = "dd/MM/yyyy")
          LocalDate dataInicio,
          @RequestParam(name = "dataFim", required = false)
          @DateTimeFormat(pattern = "dd/MM/yyyy")
          LocalDate dataFim,
          @RequestParam(name = "valorMinimo", required = false)
          Float valorMinimo
          ) {
    return shopService.getShopByFilter(dataInicio, dataFim, valorMinimo);
  }

  @GetMapping("/report")
  public ShopReportDto getReport(
          @RequestParam(name = "dataInicio", required = true)
          @DateTimeFormat(pattern = "dd/MM/yyyy")
          LocalDate dataInicio,
          @RequestParam(name = "dataFim", required = true)
          @DateTimeFormat(pattern = "dd/MM/yyyy")
          LocalDate dataFim
  ) {
    return shopService.getReportByDate(dataInicio, dataFim);
  }


}
