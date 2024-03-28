package com.marcos.silva.rodrigues.shoppingapi;

import com.marcos.silva.rodrigues.UserDto;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class UserService {

  private static final String userApiUrl = "http://localhost:8080";

  public UserDto getUserByCpf(String cpf) {
    try {
      WebClient webClient = WebClient.builder()
              .baseUrl(userApiUrl)
              .build();

      Mono<UserDto> user = webClient.get()
              .uri("/user/" + cpf + "/cpf")
              .retrieve()
              .bodyToMono(UserDto.class);
      return  user.block();
    } catch (Exception e) {
      throw new RuntimeException("User not found");
    }
  }
}
