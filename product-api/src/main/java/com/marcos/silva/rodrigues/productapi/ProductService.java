package com.marcos.silva.rodrigues.productapi;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
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
            .map(ProductDto::convert)
            .collect(Collectors.toList());
  }

  public Page<ProductDto> getAllPage(Pageable page) {
    Page<Product> products = productRepository.findAll(page);
    return products
            .map(ProductDto::convert);
  }

  public List<ProductDto> getProductsByCategory(Long categoryId) {
    List<Product> products = productRepository.getProductByCategory(categoryId);
    return products
            .stream()
            .map(ProductDto::convert)
            .collect(Collectors.toList());
  }

  public ProductDto findByProductIdentifier(String productIdentifier) {
    Product product = productRepository.findByProductIdentifier(productIdentifier);

    if (product != null) {
      return ProductDto.convert(product);
    }

    return null;
  }

  public ProductDto save(ProductDto dto) {
    Product product = productRepository.save(Product.convert(dto));
    return ProductDto.convert(product);
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

    return ProductDto.convert(productRepository.save(product));
  }
}
