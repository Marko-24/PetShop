package com.marko.petstore.service;

import com.marko.petstore.model.Pet;
import com.marko.petstore.model.User;

import java.util.List;

public interface PetService {

    Pet findPetById(Long id);
    List<Pet> listAllPets();
    List<Pet> createPet(List<Pet> pets);
    boolean buy(User user, Pet pet);
}
