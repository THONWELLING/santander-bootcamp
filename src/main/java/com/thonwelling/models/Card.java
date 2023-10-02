package com.thonwelling.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class Card {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long cardId;

  @Column(unique = true, length = 16)
  private String cardNumber;

  @Column(precision = 13, scale = 2)
  private BigDecimal availableLimit;
}
