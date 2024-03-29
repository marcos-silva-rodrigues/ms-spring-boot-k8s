package com.marcos.silva.rodrigues.userapi.service;

import com.marcos.silva.rodrigues.dto.UserDto;
import com.marcos.silva.rodrigues.userapi.model.User;
import com.marcos.silva.rodrigues.userapi.repository.UserRepository;
import com.marcos.silva.rodrigues.userapi.utils.DtoConverter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

  @InjectMocks
  private UserService userService;

  @Mock
  private UserRepository userRepository;

  public static User getUser(Integer id, String nome, String cpf) {
    User user = new User();
    user.setId(Long.valueOf(id));
    user.setCpf(cpf);
    user.setNome(nome);
    user.setTelefone("91111-1111");
    user.setEndereco("Rua D");
    return user;
  }

  @Test
  public void testListAllUser() {
    List<User> list = new ArrayList<>();
    list.add(getUser(1, "User name", "123"));
    list.add(getUser(2, "User name 2", "456"));

    Mockito.when(userRepository.findAll()).thenReturn(list);

    List<UserDto> userReturn = userService.getAll();

    Assertions.assertEquals(2, userReturn.size());
  }

  @Test
  public void testSaveNewUser() {
    User userDb = getUser(1, "User name", "123");
    UserDto dto = DtoConverter.convert(userDb);

    Mockito.when(userRepository.save(Mockito.any())).thenReturn(userDb);
    UserDto user = userService.save(dto);

    Assertions.assertEquals("User name", user.getNome());
    Assertions.assertEquals("123", user.getCpf());
  }

  @Test
  public void testEditUser() {
    User userDb = getUser(1, "User name", "123");

    Mockito.when(userRepository.findById(1L)).thenReturn(Optional.of(userDb));
    Mockito.when(userRepository.save(Mockito.any())).thenReturn(userDb);

    UserDto userDto = DtoConverter.convert(userDb);
    userDto.setEndereco("Novo endereco");
    userDb.setEmail("1234");

    UserDto user = userService.editUser(1L, userDto);
    Assertions.assertEquals("Novo endereco", user.getEndereco());
    Assertions.assertEquals("91111-1111", user.getTelefone());
  }
}