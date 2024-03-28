package com.marcos.silva.rodrigues.shoppingapi;

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
