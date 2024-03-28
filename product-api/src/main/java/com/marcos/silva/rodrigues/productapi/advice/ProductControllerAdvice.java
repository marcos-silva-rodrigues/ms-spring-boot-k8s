package com.marcos.silva.rodrigues.productapi.advice;

import com.marcos.silva.rodrigues.dto.ErrorDto;
import com.marcos.silva.rodrigues.exception.CategoryNotFoundException;
import com.marcos.silva.rodrigues.exception.ProductNotFoundException;
import com.marcos.silva.rodrigues.exception.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.time.LocalDateTime;
import java.util.List;

@ControllerAdvice(basePackages = "com.marcos.silva.rodrigues.productapi.controller")
public class ProductControllerAdvice {


  @ResponseBody
  @ResponseStatus(HttpStatus.NOT_FOUND)
  @ExceptionHandler(ProductNotFoundException.class)
  public ErrorDto handleProductNotFound(ProductNotFoundException ex) {
    ErrorDto dto = new ErrorDto();
    dto.setStatus(HttpStatus.NOT_FOUND.value());
    dto.setMessage("Produto não encontrado.");
    dto.setTimestamp(LocalDateTime.now());

    return dto;
  }

  @ResponseBody
  @ResponseStatus(HttpStatus.NOT_FOUND)
  @ExceptionHandler(CategoryNotFoundException.class)
  public ErrorDto handleCategoryNotFound(CategoryNotFoundException ex) {
    ErrorDto dto = new ErrorDto();
    dto.setStatus(HttpStatus.NOT_FOUND.value());
    dto.setMessage("Categoria não encontrado.");
    dto.setTimestamp(LocalDateTime.now());

    return dto;
  }

  @ResponseBody
  @ResponseStatus(HttpStatus.NOT_FOUND)
  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ErrorDto handleValidationRequired(MethodArgumentNotValidException ex) {
    ErrorDto dto = new ErrorDto();
    dto.setStatus(HttpStatus.BAD_REQUEST.value());

    List<FieldError> errors = ex.getBindingResult()
            .getFieldErrors();

    StringBuilder sb = new StringBuilder("Valor inválido para o(s) campo(s): ");
    for (FieldError fieldError: errors) {
      sb.append(" ");
      sb.append(fieldError.getField());
    }

    dto.setMessage(sb.toString());
    dto.setTimestamp(LocalDateTime.now());

    return dto;
  }


}
