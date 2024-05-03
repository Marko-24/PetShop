package com.marko.petstore.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@NoArgsConstructor
@Data
@Entity
public class Pet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private User owner;

    private String name;

    @Enumerated(EnumType.STRING)
    private PetType petType;

    private String description;

    private LocalDate dateOfBirth;

    private int price;

    private int rating;

    public Pet(User owner, String name, PetType petType, String description, LocalDate dateOfBirth, int price) {
        this.owner = owner;
        this.name = name;
        this.petType = petType;
        this.description = description;
        this.dateOfBirth = dateOfBirth;
        this.price = price;
    }

    public Pet(User owner, String name, PetType petType, String description, LocalDate dateOfBirth, int price, int rating) {
        this.owner = owner;
        this.name = name;
        this.petType = petType;
        this.description = description;
        this.dateOfBirth = dateOfBirth;
        this.price = price;
        this.rating = rating;
    }
}
