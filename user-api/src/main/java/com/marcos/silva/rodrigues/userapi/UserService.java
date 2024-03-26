package com.marcos.silva.rodrigues.userapi;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {

  @Autowired
  private UserRepository userRepository;

  public List<UserDto> getAll() {
    List<User> all = userRepository.findAll();
    return all
            .stream()
            .map(UserDto::convert)
            .collect(Collectors.toList());
  }

  public UserDto findById(Long id) {
    User u = userRepository.findById(id).orElseThrow(() -> new RuntimeException("user not found"));

    return UserDto.convert(u);
  }

  public UserDto save(UserDto dto) {
    dto.setDataCadastro(LocalDateTime.now());
    User user = userRepository.save(User.convert(dto));
    return UserDto.convert(user);
  }

  public UserDto delete(Long id) {
    User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("user not found"));

    userRepository.delete(user);
    return UserDto.convert(user);
  }

  public UserDto findByCpf(String cpf) {
    User u = userRepository.findByCpf(cpf);

    if (u != null) {
      return UserDto.convert(u);

    }

    return null;
  }

  public List<UserDto> queryByName(String name) {
    List<User> search = userRepository.queryByNomeLike(name);
    return search
            .stream()
            .map(UserDto::convert)
            .collect(Collectors.toList());
  }

  public UserDto editUser(Long userId, UserDto userDto) {
    User user = userRepository
            .findById(userId)
            .orElseThrow(() -> new RuntimeException("user not found"));

    if (userDto.getEmail() != null && !user.getEmail().equals(userDto.getEmail())) {
      user.setEmail(userDto.getEmail());
    }

    if (userDto.getTelefone() != null && !user.getTelefone().equals(userDto.getTelefone())) {
      user.setTelefone(userDto.getTelefone());
    }

    if (userDto.getEndereco() != null && !user.getEndereco().equals(userDto.getEndereco())) {
      user.setEndereco(userDto.getEndereco());
    }

    user = userRepository.save(user);
    return UserDto.convert(user);
  }

  public Page<UserDto> getAllPage(Pageable page) {
    Page<User> users = userRepository.findAll(page);
    return users.map(UserDto::convert);
  }




}
