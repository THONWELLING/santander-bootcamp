package com.thonwelling.service.impl;

import com.thonwelling.exceptions.BusinessException;
import com.thonwelling.models.User;
import com.thonwelling.repositories.UserRepository;
import com.thonwelling.service.UserService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.Optional.ofNullable;

@Service
public class UserServiceImpl implements UserService {

  /**
   * ID de usuário utilizado na Santander Dev Week 2023.
   * Por isso, vamos criar algumas regras para mantê-lo integro.
   */
  private static final Long UNCHANGEABLE_USER_ID = 1L;
  private final UserRepository userRepository;

  public UserServiceImpl(UserRepository userRepository){
    this.userRepository = userRepository;
  }

  @Transactional()
  public List<User> findAll() {
    return this.userRepository.findAll();
  }

  @Transactional()
  public User findById(Long id) {
    return this.userRepository.findById(id).orElseThrow();
  }

  @Transactional
  public User create(User userToCreate) throws BusinessException {
    ofNullable(userToCreate).orElseThrow();
    ofNullable(userToCreate.getAccount()).orElseThrow();
    ofNullable(userToCreate.getCard()).orElseThrow(() -> new BusinessException("User card must not be null."));

    this.validateChangeableId(userToCreate.getUserId(), "created");
    if (userRepository.existsByAccountNumber(userToCreate.getAccount().getNumber())) {
      throw new BusinessException("This account number already exists.");
    }
    if (userRepository.existsByCardNumber(userToCreate.getCard().getNumber())) {
      throw new BusinessException("This card number already exists.");
    }
    return this.userRepository.save(userToCreate);
  }

  @Transactional
  public User update(Long id, User userToUpdate) throws BusinessException {
    this.validateChangeableId(id, "updated");
    User userPersisted = this.findById(id);
    if (!userPersisted.getUserId().equals(userToUpdate.getUserId())) {
      throw new BusinessException("Update IDs must be the same.");
    }

    userPersisted.setUserName(userToUpdate.getUserName());
    userPersisted.setAccount(userToUpdate.getAccount());
    userPersisted.setCard(userToUpdate.getCard());
    userPersisted.setFeatures(userToUpdate.getFeatures());
    userPersisted.setNews(userToUpdate.getNews());

    return this.userRepository.save(userPersisted);
  }

  @Transactional
  public void delete(Long id) throws BusinessException {
    this.validateChangeableId(id, "deleted");
    User userPersisted = this.findById(id);
    this.userRepository.delete(userPersisted);
  }

  private void validateChangeableId(Long id, String operation) throws BusinessException {
    if (UNCHANGEABLE_USER_ID.equals(id)) {
      throw new BusinessException("User with ID %d can not be %s.".formatted(UNCHANGEABLE_USER_ID, operation));
    }
  }
}