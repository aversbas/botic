package com.orto.botic.entities;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "image")
@Data
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "path")
    private String path;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

}
