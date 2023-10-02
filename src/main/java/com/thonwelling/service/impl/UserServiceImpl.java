package com.thonwelling.service.impl;

import com.thonwelling.models.User;
import com.thonwelling.repositories.UserRepository;
import com.thonwelling.service.UserService;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class UserServiceImpl implements UserService {

  private final UserRepository userRepository;

  public UserServiceImpl(UserRepository userRepository){
    this.userRepository = userRepository;
  }

  @Override
  public User findById(Long id) {
    return userRepository.findById(id).orElseThrow(NoSuchElementException::new);
  }

  @Override
  public User create(User userToCreate) {
    if (userToCreate.getUserId() != null && userRepository.existsById(userToCreate.getUserId())){
      throw new IllegalArgumentException("This User Id Already Exists!");
    }
    return userRepository.save(userToCreate);
  }
}
