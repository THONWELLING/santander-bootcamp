package com.thonwelling.models.dtos;

import com.thonwelling.models.Account;

import java.math.BigDecimal;

public record AccountDto (Long id, String number, String agency, BigDecimal balance, BigDecimal limit){

    public AccountDto(Account model) {
      this(model.getId(), model.getNumber(), model.getAccountAgency(), model.getAccountBalance(), model.getAdictionalLimit());
    }

    public Account toModel() {
      Account model = new Account();
      model.setId(this.id);
      model.setNumber(this.number);
      model.setAccountAgency(this.agency);
      model.setAccountBalance(this.balance);
      model.setAdictionalLimit(this.limit);
      return model;
    }
}
