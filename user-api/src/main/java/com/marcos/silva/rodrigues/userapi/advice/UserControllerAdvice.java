package com.marcos.silva.rodrigues.userapi.advice;

import com.marcos.silva.rodrigues.dto.ErrorDto;
import com.marcos.silva.rodrigues.exception.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.time.LocalDateTime;

@ControllerAdvice(basePackages = "com.marcos.silva.rodrigues.userapi.controller")
public class UserControllerAdvice {

  @ResponseBody
  @ResponseStatus(HttpStatus.NOT_FOUND)
  @ExceptionHandler(UserNotFoundException.class)
  public ErrorDto handleUserNotFound(UserNotFoundException ex) {
    ErrorDto dto = new ErrorDto();
    dto.setStatus(HttpStatus.NOT_FOUND.value());
    dto.setMessage("Usuário não encontrado.");
    dto.setTimestamp(LocalDateTime.now());

    return dto;
  }
}
