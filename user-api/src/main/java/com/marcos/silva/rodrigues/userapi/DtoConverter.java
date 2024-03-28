package com.marcos.silva.rodrigues.userapi;

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
}
