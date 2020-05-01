package com.orto.botic.entities;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name = "product")
@Data
@NoArgsConstructor
public class Product {

  @Id
  @Column(name = "id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "title")
  private String title;

  @Column(name = "description")
  private String description;

  @Column(name = "price")
  private int price;

  @Column(name = "photo_name")
  private String photoName;

  @Column(name = "brand")
  private String brand;

  @Column(name = "color")
  private String color;

  @Column(name = "size")
  private int size;

  @Column(name = "season")
  private String season;

  @Column(name = "quantity")
  private int quantity;

  @Column(name = "appointment")
  private String appointment;

  @Column(name = "article")
  private String article;

  @ToString.Exclude
  @ManyToOne
  private Category category;

  @ToString.Exclude
  @ManyToOne
  private ProductType type;
}
