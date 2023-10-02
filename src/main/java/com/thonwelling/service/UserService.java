package com.thonwelling.service;

import com.thonwelling.models.User;

public interface UserService {
  User findById(Long id);

  User create(User userToCreate);
}
