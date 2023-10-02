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
public class Account {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @Column(unique = true, length = 8)
  private String accountNumber;
  private String accountAgency;

  @Column(precision = 13, scale = 2, nullable = false)
  private BigDecimal accountBalance;

  @Column(precision = 13, scale = 2)
  private BigDecimal adictionalLimit;
}
