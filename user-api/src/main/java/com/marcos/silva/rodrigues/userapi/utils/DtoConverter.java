package com.marcos.silva.rodrigues.userapi.utils;

import com.marcos.silva.rodrigues.dto.UserDto;
import com.marcos.silva.rodrigues.userapi.model.User;

public class DtoConverter {

  public static UserDto convert(User u) {
    UserDto dto  = new UserDto();
    dto.setNome(u.getNome());
    dto.setCpf(u.getCpf());
    dto.setEndereco(u.getEndereco());
    dto.setEmail(u.getEmail());
    dto.setTelefone(u.getTelefone());
    dto.setDataCadastro(u.getDataCadastro());

    return dto;
  }

  public static User convert(UserDto dto) {
    User u  = new User();
    u.setNome(dto.getNome());
    u.setCpf(dto.getCpf());
    u.setEndereco(dto.getEndereco());
    u.setEmail(dto.getEmail());
    u.setTelefone(dto.getTelefone());
    u.setDataCadastro(dto.getDataCadastro());

    return u;
  }
}
