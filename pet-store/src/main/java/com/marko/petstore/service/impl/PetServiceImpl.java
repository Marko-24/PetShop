package com.marko.petstore.service.impl;

import com.marko.petstore.model.Pet;
import com.marko.petstore.model.PetType;
import com.marko.petstore.model.User;
import com.marko.petstore.model.exceptions.InvalidPetIdException;
import com.marko.petstore.repository.PetRepository;
import com.marko.petstore.service.PetService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
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
            if (pet.getPetType() == PetType.Cat) {
                pet.setPrice(Period.between(pet.getDateOfBirth(), LocalDate.now()).getYears());
                createdPets.add(petRepository.save(pet));
            } else if (pet.getPetType() == PetType.Dog) {
                boolean ratingSet = pet.getRating() >= 0;
                if (!ratingSet || pet.getRating() > 10) {
                    throw new IllegalArgumentException("Invalid rating for dog.");
                }
                int basePrice = Period.between(pet.getDateOfBirth(), LocalDate.now()).getYears();
                int ratingPrice = pet.getRating();
                pet.setPrice(basePrice + ratingPrice);
                createdPets.add(petRepository.save(pet));
            }
        }
        return createdPets;
    }

    @Override
    public boolean buy(User user, Pet pet) {
        if (pet.getPetType() == PetType.Cat) {
            return buyCat(user, pet);
        } else if (pet.getPetType() == PetType.Dog) {
            return buyDog(user, pet);
        } else {
            System.out.println("Unknown pet type.");
            return false;
        }
    }

    private boolean buyCat(User user, Pet pet) {
        int budgetDifference = user.getBudget() - pet.getPrice();
        if (budgetDifference >= 0 && pet.getOwner() == null) {
            user.setBudget(budgetDifference);
            pet.setOwner(user);
            petRepository.save(pet);
            System.out.println("Meow, cat " + pet.getName() + " has owner " + user.getFirstName());
            return true;
        } else {
            System.out.println("Unable to buy cat.");
            return false;
        }
    }

    private boolean buyDog(User user, Pet pet) {
        int budgetDifference = user.getBudget() - pet.getPrice();
        if (budgetDifference >= 0 && pet.getOwner() == null) {
            user.setBudget(budgetDifference);
            pet.setOwner(user);
            petRepository.save(pet);
            System.out.println("Woof, dog " + pet.getName() + " has owner " + user.getFirstName());
            return true;
        } else {
            System.out.println("Unable to buy dog.");
            return false;
        }
    }
}