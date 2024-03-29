package com.marcos.silva.rodrigues.shoppingapi.service;

import com.marcos.silva.rodrigues.dto.ProductDto;
import com.marcos.silva.rodrigues.exception.ProductNotFoundException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class ProductService {

  @Value("${PRODUCT_API_URL:http://localhost:54880}")
  private String productApiUrl;

  public ProductDto getProductByIdentifier(String productIdentifier) {
    try {
      WebClient webClient = WebClient.builder()
              .baseUrl(productApiUrl)
              .build();

      Mono<ProductDto> product = webClient.get()
              .uri("/product/" + productIdentifier)
              .retrieve()
              .bodyToMono(ProductDto.class);

      return product.block();
    } catch (Exception e) {
      throw new ProductNotFoundException();
    }
  }
}
