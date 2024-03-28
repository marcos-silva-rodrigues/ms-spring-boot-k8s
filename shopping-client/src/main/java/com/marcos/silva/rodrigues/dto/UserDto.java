package com.marcos.silva.rodrigues.dto;

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
  private String key;
  @NotBlank(message = "Email é obrigatório")
  private String email;
  private String telefone;
  private LocalDateTime dataCadastro;


}
