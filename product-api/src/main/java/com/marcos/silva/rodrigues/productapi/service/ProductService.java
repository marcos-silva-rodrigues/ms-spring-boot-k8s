package com.marcos.silva.rodrigues.productapi.service;

import com.marcos.silva.rodrigues.dto.ProductDto;
import com.marcos.silva.rodrigues.productapi.model.Product;
import com.marcos.silva.rodrigues.productapi.repository.ProductRepository;
import com.marcos.silva.rodrigues.productapi.utils.DtoConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {

  private final ProductRepository productRepository;

  public List<ProductDto> getAll() {
    List<Product> products = productRepository.findAll();
    return products
            .stream()
            .map(DtoConverter::convert)
            .collect(Collectors.toList());
  }

  public Page<ProductDto> getAllPage(Pageable page) {
    Page<Product> products = productRepository.findAll(page);
    return products
            .map(DtoConverter::convert);
  }

  public List<ProductDto> getProductsByCategory(Long categoryId) {
    List<Product> products = productRepository.getProductByCategory(categoryId);
    return products
            .stream()
            .map(DtoConverter::convert)
            .collect(Collectors.toList());
  }

  public ProductDto findByProductIdentifier(String productIdentifier) {
    Product product = productRepository.findByProductIdentifier(productIdentifier);

    if (product != null) {
      return DtoConverter.convert(product);
    }

    return null;
  }

  public ProductDto save(ProductDto dto) {
    Product product = productRepository.save(DtoConverter.convert(dto));
    return DtoConverter.convert(product);
  }


  public void delete(Long productId) {
    Optional<Product> product = productRepository.findById(productId);
    product.ifPresent(productRepository::delete);
  }

  public ProductDto editProduct(Long id, ProductDto dto) {
   Product product = productRepository
           .findById(id)
           .orElseThrow(() -> new RuntimeException("product not found"));

    if (dto.getNome() != null && !dto.getNome().isEmpty()) {
      product.setNome(dto.getNome());
    }

    if (dto.getPreco() != null) {
      product.setPreco(dto.getPreco());
    }

    return DtoConverter.convert(productRepository.save(product));
  }
}
