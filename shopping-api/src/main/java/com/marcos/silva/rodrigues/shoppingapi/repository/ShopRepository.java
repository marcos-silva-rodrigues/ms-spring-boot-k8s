package com.marcos.silva.rodrigues.shoppingapi.repository;

import com.marcos.silva.rodrigues.shoppingapi.model.Shop;
import com.marcos.silva.rodrigues.shoppingapi.repository.ReportRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ShopRepository extends JpaRepository<Shop, Long>, ReportRepository {

  public List<Shop> findAllByUserIdentifier(String userIdentifier);
  public List<Shop> findAllByTotalGreaterThan(Float total);

  public List<Shop> findAllByDateGreaterThan(LocalDateTime date);
}
