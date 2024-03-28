package com.marcos.silva.rodrigues.userapi.service;

import com.marcos.silva.rodrigues.dto.UserDto;
import com.marcos.silva.rodrigues.exception.UserNotFoundException;
import com.marcos.silva.rodrigues.userapi.model.User;
import com.marcos.silva.rodrigues.userapi.repository.UserRepository;
import com.marcos.silva.rodrigues.userapi.utils.DtoConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
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
            .map(DtoConverter::convert)
            .collect(Collectors.toList());
  }

  public UserDto findById(Long id) {
    User u = userRepository.findById(id).orElseThrow(() -> new RuntimeException("user not found"));

    return DtoConverter.convert(u);
  }

  public UserDto save(UserDto dto) {
    dto.setKey(UUID.randomUUID().toString());
    dto.setDataCadastro(LocalDateTime.now());
    User user = userRepository.save(DtoConverter.convert(dto));
    return DtoConverter.convert(user);
  }

  public UserDto delete(Long id) {
    User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("user not found"));

    userRepository.delete(user);
    return DtoConverter.convert(user);
  }

  public UserDto findByCpfAndKey(String cpf, String key) {
    User u = userRepository.findByCpfAndKey(cpf, key);

    if (u != null) {
      return DtoConverter.convert(u);

    }

    throw new UserNotFoundException();
  }

  public List<UserDto> queryByName(String name) {
    List<User> search = userRepository.queryByNomeLike(name);
    return search
            .stream()
            .map(DtoConverter::convert)
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
    return DtoConverter.convert(user);
  }

  public Page<UserDto> getAllPage(Pageable page) {
    Page<User> users = userRepository.findAll(page);
    return users.map(DtoConverter::convert);
  }




}
