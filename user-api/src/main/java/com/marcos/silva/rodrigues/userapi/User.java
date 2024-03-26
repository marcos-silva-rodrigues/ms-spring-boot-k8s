package com.marcos.silva.rodrigues.userapi;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String nome;
  private String cpf;
  private String endereco;
  private String email;
  private String telefone;
  private LocalDateTime dataCadastro;

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
