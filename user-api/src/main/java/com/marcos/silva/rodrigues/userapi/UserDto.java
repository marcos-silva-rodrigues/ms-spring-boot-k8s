package com.marcos.silva.rodrigues.userapi;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

  @NotBlank(message = "Nome é obrigatório")
  private String nome;
  @NotBlank(message = "Cpf é obrigatório")
  private String cpf;
  private String endereco;
  @NotBlank(message = "Email é obrigatório")
  private String email;
  private String telefone;
  private LocalDateTime dataCadastro;

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
