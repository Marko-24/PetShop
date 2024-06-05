package com.marko.petstore.model;

import java.time.LocalDate;
import java.time.Period;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class Dog extends Pet {

    private int rating;

    public Dog(User owner, String name, String description, LocalDate dateOfBirth, int price, int rating) {
        this.owner = owner;
        this.name = name;
        this.description = description;
        this.dateOfBirth = dateOfBirth;
        this.price = price;
        this.rating = rating;
    }

    @Override
    public int calculatePrice() {
        int basePrice = Period.between(this.dateOfBirth, LocalDate.now()).getYears();
        return basePrice + this.rating;
    }

    @Override
    public String toString() {
        return "Dog{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", price=" + price +
                ", rating=" + rating +
                ", owner=" + (owner != null ? owner.getFirstName() + " " + owner.getLastName() : "none") +
                '}';
    }
}