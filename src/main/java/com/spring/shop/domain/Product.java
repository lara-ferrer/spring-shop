package com.spring.shop.domain;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column
    private String name;
    @Column
    private String description;
    @Column
    private float price;
    @Column
    private String image;
    @Column(name="creation_date")
    private LocalDate creationDate;
    @ManyToOne
    @JoinColumn(name="categoryId", nullable=false)
    Category category;
    @OneToMany(mappedBy="product")
    private List<Order> orders;
}