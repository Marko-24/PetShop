package com.marko.petstore.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@EqualsAndHashCode
@Entity
@Table(name = "storeUsers")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;

    private String lastName;

    private String email;

    private int budget;

    public User(String firstName, String lastName, String email, int budget) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.budget = budget;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", budget=" + budget +
                '}';
    }
}