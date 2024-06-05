package com.marko.petstore.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.Period;

@NoArgsConstructor
@Data
@EqualsAndHashCode
@Entity
public abstract class Pet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    protected User owner;

    protected String name;

    protected String description;

    protected LocalDate dateOfBirth;

    protected int price;

    public int calculatePrice() {
        return Period.between(this.dateOfBirth, LocalDate.now()).getYears();
    }

    @Override
    public String toString() {
        return "Pet{" +
                "id=" + id +
                ", owner=" + (owner != null ? owner.getFirstName() + " " + owner.getLastName() : "none") +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", price=" + price +
                '}';
    }
}