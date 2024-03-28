package com.marcos.silva.rodrigues.productapi.model;

import com.marcos.silva.rodrigues.productapi.model.Category;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "product")
public class Product {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String nome;
  private Float preco;
  private String descricao;
  private String productIdentifier;

  @ManyToOne
  @JoinColumn(name = "category_id")
  private Category category;


}
