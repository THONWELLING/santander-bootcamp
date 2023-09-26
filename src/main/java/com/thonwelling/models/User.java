package com.thonwelling.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name="tb_user")
public class User {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long userId;
  @Column(length = 100, nullable = false)
  private String userName;

  @OneToOne(cascade = CascadeType.ALL)
  private Account account;
  @OneToOne(cascade = CascadeType.ALL)
  private Card card;

  @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
  private List<Feature> features;

  @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
  private List<News> news;
}
