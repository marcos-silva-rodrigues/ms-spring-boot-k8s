package com.marcos.silva.rodrigues.shoppingapi.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.marcos.silva.rodrigues.dto.ProductDto;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import org.aspectj.util.Reflection;
import org.junit.After;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {

  public static MockWebServer mockBackend;

  @InjectMocks
  private ProductService productService;

  @BeforeEach
  void setUp() throws IOException {
    mockBackend = new MockWebServer();
    mockBackend.start();

    String baseUrl = String.format("http://localhost:%s", mockBackend.getPort());
    ReflectionTestUtils.setField(productService, "productApiUrl", baseUrl);
  }

  @AfterEach
  void tearDown() throws IOException {
    mockBackend.shutdown();
  }

  @Test
  public void test_getProductByIdentifier() throws IOException {
    ProductDto productDto = new ProductDto();
    productDto.setPreco(1000F);
    productDto.setProductIdentifier("prod-identifier");

    ObjectMapper objectMapper = new ObjectMapper();

    mockBackend.enqueue(new MockResponse()
            .setBody(objectMapper.writeValueAsString(productDto))
            .addHeader("Content-Type", "application/json"));

    productDto = productService.getProductByIdentifier("prod-identifier");

    Assertions.assertEquals(1000F, productDto.getPreco());
    Assertions.assertEquals("prod-identifier", productDto.getProductIdentifier());
  }

}