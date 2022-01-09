package com.spring.shop.domain;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column
    private String username;
    @Column
    private String password;
    @Column
    private String name;
    @Column(name = "first_name")
    private String firstName;
    @Column
    private String email;
    @Column(name = "birth_date")
    private LocalDate birthDate;
    @OneToMany(mappedBy = "user")
    List<Order> orders;
}