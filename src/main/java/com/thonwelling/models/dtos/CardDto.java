package com.thonwelling.models.dtos;

import com.thonwelling.models.Card;

import java.math.BigDecimal;

public record CardDto(Long id, String number, BigDecimal limit) {

  public CardDto(Card model) {
    this(model.getCardId(), model.getNumber(), model.getAvailableLimit());
  }

  public Card toModel() {
    Card model = new Card();
    model.setCardId(this.id);
    model.setNumber(this.number);
    model.setAvailableLimit(this.limit);
    return model;
  }
}
