package com.marcos.silva.rodrigues.userapi;


import jakarta.annotation.PostConstruct;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

  @Autowired
  private final UserService userService;


  @GetMapping
  public List<UserDto> getUsers() {
    return userService.getAll();
  }

  @GetMapping("/{id}")
  public UserDto getUserById(@PathVariable Long id) {
    return userService.findById(id);
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public UserDto create(@RequestBody @Valid UserDto userDto) {
    return userService.save(userDto);
  }

  @GetMapping("/{cpf}/cpf")
  public UserDto getUserById(@PathVariable String cpf) {
    return userService.findByCpf(cpf);
  }

  @DeleteMapping("/{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void delete(@PathVariable Long id) {
    userService.delete(id);
  }

  @GetMapping("/search")
  public List<UserDto> queryByName(@RequestParam(name = "nome", required = true) String nome) {
    return userService.queryByName(nome);
  }

  @PatchMapping("/{id}")
  public UserDto editUser(@PathVariable Long id, @RequestBody @Valid UserDto dto) {
    return userService.editUser(id, dto);
  }

  @GetMapping("/pageable")
  public Page<UserDto> getUsersPage(Pageable pageable) {
    return userService.getAllPage(pageable);
  }

}
