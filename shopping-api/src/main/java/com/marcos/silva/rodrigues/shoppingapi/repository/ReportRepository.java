package com.marcos.silva.rodrigues.shoppingapi.repository;

import com.marcos.silva.rodrigues.dto.ShopReportDto;
import com.marcos.silva.rodrigues.shoppingapi.model.Shop;

import java.time.LocalDate;
import java.util.List;

public interface ReportRepository {

  public List<Shop> getShoByFilters(
          LocalDate dataInicio,
          LocalDate dataFim,
          Float valorMinimo
  );

  public ShopReportDto getReportByDate(
          LocalDate dataInicio,
          LocalDate dataFim
  );
}
