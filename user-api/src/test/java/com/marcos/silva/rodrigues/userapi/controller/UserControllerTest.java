package com.marcos.silva.rodrigues.userapi.controller;

import com.marcos.silva.rodrigues.dto.UserDto;
import com.marcos.silva.rodrigues.userapi.service.UserService;
import com.marcos.silva.rodrigues.userapi.service.UserServiceTest;
import com.marcos.silva.rodrigues.userapi.utils.DtoConverter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class UserControllerTest {

  @InjectMocks
  private UserController userController;

  @Mock
  private UserService userService;
  private MockMvc mockMvc;

  @BeforeEach
  public void setup() {
    mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
  }

  @Test
  public void testListUsers() throws Exception {
    List<UserDto> users = new ArrayList<>();
    users.add(DtoConverter.convert(UserServiceTest.getUser(1, "Nome 1", "123")));

    Mockito.when(userService.getAll()).thenReturn(users);

    MvcResult result = mockMvc
            .perform(MockMvcRequestBuilders.get("/user"))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andReturn();

    String resp = result.getResponse().getContentAsString();
    StringBuilder expect = new StringBuilder();
    expect.append("[{\"nome\":\"Nome 1\",\"cpf\":\"123\",");
    expect.append("\"endereco\":\"Rua D\",\"key\":null,\"email\":null,");
    expect.append("\"telefone\":\"91111-1111\",\"dataCadastro\":null}]");
    Assertions.assertEquals(expect.toString(), resp);
  }

}