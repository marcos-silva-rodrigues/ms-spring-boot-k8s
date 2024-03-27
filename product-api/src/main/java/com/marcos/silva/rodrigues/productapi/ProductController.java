package com.marcos.silva.rodrigues.productapi;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
@RequiredArgsConstructor
public class ProductController {

  private final ProductService productService;

  @GetMapping
  public List<ProductDto> getAll() {
    return productService.getAll();
  }

  @GetMapping("/pageable")
  public Page<ProductDto> getAllPage (@PageableDefault Pageable page) {
    return productService.getAllPage(page);
  }

  @GetMapping("/category/{categoryId}")
  public List<ProductDto> getByProductIdentifier(@PathVariable Long categoryId) {
    return productService.getProductsByCategory(categoryId);
  }

  @GetMapping("/{productIdentifier}")
  public ProductDto getByProductIdentifier(@PathVariable String productIdentifier) {
    return productService.findByProductIdentifier(productIdentifier);
  }

  @PostMapping
  public ProductDto create(@Valid @RequestBody ProductDto dto) {
    return productService.save(dto);
  }

  @DeleteMapping("/{id}")
  public void delete(@PathVariable Long id) {
    productService.delete(id);
  }

  @PatchMapping("/{id}")
  public ProductDto create(@PathVariable Long id, @Valid @RequestBody ProductDto dto) {
    return productService.editProduct(id, dto);
  }



}
