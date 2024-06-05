package com.marko.petstore.model;

import java.time.LocalDate;
import java.time.Period;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class Cat extends Pet {

    public Cat(User owner, String name, String description, LocalDate dateOfBirth, int price) {
        this.owner = owner;
        this.name = name;
        this.description = description;
        this.dateOfBirth = dateOfBirth;
        this.price = price;
    }

    @Override
    public int calculatePrice() {
        return Period.between(this.dateOfBirth, LocalDate.now()).getYears();
    }

    @Override
    public String toString() {
        return "Cat{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", price=" + price +
                ", owner=" + (owner != null ? owner.getFirstName() + " " + owner.getLastName() : "none") +
                '}';
    }
}