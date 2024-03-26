package com.marcos.silva.rodrigues.userapi;


import jakarta.annotation.PostConstruct;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

  private static List<UserDto> usuarios = new ArrayList<UserDto>();

  @PostConstruct
  public void initializeList() {
    UserDto userDto = new UserDto();
    userDto.setNome("Eduardo");
    userDto.setCpf("123");
    userDto.setEndereco("Rua A");
    userDto.setEmail("eduardo@email.com");
    userDto.setTelefone("1234-5678");
    userDto.setDataCadastro(LocalDateTime.now());

    UserDto userDto2 = new UserDto();
    userDto2.setNome("Jose");
    userDto2.setCpf("456");
    userDto2.setEndereco("Rua N");
    userDto2.setEmail("jose@email.com");
    userDto2.setTelefone("4311-5678");
    userDto2.setDataCadastro(LocalDateTime.now());

    UserDto userDto3 = new UserDto();
    userDto3.setNome("Rose");
    userDto3.setCpf("098");
    userDto3.setEndereco("Rua R");
    userDto3.setEmail("rose@email.com");
    userDto3.setTelefone("5342-0987");
    userDto3.setDataCadastro(LocalDateTime.now());

    usuarios.add(userDto);
    usuarios.add(userDto2);
    usuarios.add(userDto3);
  }


  @GetMapping
  public List<UserDto> getMessage() {
    return usuarios;
  }

  @GetMapping("/{cpf}")
  public UserDto getUsersFiltro(@PathVariable String cpf) {
    return usuarios
            .stream()
            .filter(userDto -> userDto.getCpf().equals(cpf))
            .findFirst()
            .orElseThrow(() -> new RuntimeException("user not found"));
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public UserDto create(@RequestBody @Valid UserDto userDto) {
    userDto.setDataCadastro(LocalDateTime.now());
    usuarios.add(userDto);
    return userDto;
  }

  @DeleteMapping("/{cpf}")
  public boolean delete(@PathVariable String cpf) {
    return usuarios.removeIf(userDto -> userDto.getCpf().equals(cpf));
  }

}
