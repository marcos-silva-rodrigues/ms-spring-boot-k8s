package com.marcos.silva.rodrigues.shoppingapi.service;

import com.marcos.silva.rodrigues.dto.UserDto;
import com.marcos.silva.rodrigues.exception.UserNotFoundException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class UserService {

  @Value("${USER_API_URL:http://localhost:8080}")
  private String userApiUrl;

  public UserDto getUserByCpfAndKey(String cpf, String key) {
    try {
      WebClient webClient = WebClient.builder()
              .baseUrl(userApiUrl)
              .build();

      Mono<UserDto> user = webClient.get()
              .uri("/user/" + cpf + "/cpf?key=" + key)
              .retrieve()
              .bodyToMono(UserDto.class);
      return  user.block();
    } catch (Exception e) {
      throw new UserNotFoundException();
    }
  }
}
