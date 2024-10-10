package com.devchangetheworld.ewebsite.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String brand;
    private String description;
    private BigDecimal price;
    private int inventory;

    //Check again the cascade in those many
    //cascade = CascadeType.ALL in Many side is so wrong
    //delete it ==> delete parent size (if orphan remove then it remove all)
    @ManyToOne(fetch = FetchType.LAZY, optional = false /*,cascade = CascadeType.ALL*/)
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Image> images;
}
