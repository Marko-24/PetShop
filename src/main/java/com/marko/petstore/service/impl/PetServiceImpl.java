package com.marko.petstore.service.impl;

import com.marko.petstore.model.Cat;
import com.marko.petstore.model.Dog;
import com.marko.petstore.model.Pet;
import com.marko.petstore.model.User;
import com.marko.petstore.model.exceptions.InvalidPetIdException;
import com.marko.petstore.repository.PetRepository;
import com.marko.petstore.service.PetService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class PetServiceImpl implements PetService {

    private final PetRepository petRepository;

    @Override
    public Pet findPetById(Long id) {
        return petRepository.findById(id).orElseThrow(InvalidPetIdException::new);
    }

    @Override
    public List<Pet> listAllPets() {
        return petRepository.findAll();
    }

    @Override
    public List<Pet> createPet(List<Pet> pets) {
        List<Pet> createdPets = new ArrayList<>();
        for (Pet pet : pets) {
            if (pet instanceof Cat cat) {
                cat.setPrice(cat.calculatePrice());
                createdPets.add(petRepository.save(cat));
            } else if (pet instanceof Dog dog) {
                boolean ratingSet = dog.getRating() >= 0;
                if (!ratingSet || dog.getRating() > 10) {
                    throw new IllegalArgumentException("Invalid rating for dog.");
                }
                dog.setPrice(dog.calculatePrice());
                createdPets.add(petRepository.save(dog));
            }
        }
        return createdPets;
    }

    @Override
    public boolean buy(User user, Pet pet) {
        int price = pet.calculatePrice();
        if (user.getBudget() > price && pet.getOwner() == null) {
            user.setBudget(user.getBudget() - price);
            pet.setOwner(user);
            petRepository.save(pet);

            if (pet instanceof Cat) {
                System.out.println("Meow, cat " + pet.getName() + " has owner " + user.getFirstName());
            } else if (pet instanceof Dog) {
                System.out.println("Woof, dog " + pet.getName() + " has owner " + user.getFirstName());
            } else {
                System.out.println(user + " bought " + pet);
            }

            return true;
        } else {
            System.out.println(user + " unable to buy " + pet);
            return false;
        }
    }
}