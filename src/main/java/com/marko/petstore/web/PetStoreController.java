package com.marko.petstore.web;

import com.marko.petstore.model.Pet;
import com.marko.petstore.model.PetType;
import com.marko.petstore.model.User;
import com.marko.petstore.service.PetService;
import com.marko.petstore.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/store")
public class PetStoreController {

    private final PetService petService;
    private final UserService userService;

    @GetMapping("/pets")
    public ResponseEntity<List<Pet>> listAllPets() {
        List<Pet> pets = petService.listAllPets();
        return ResponseEntity.ok(pets);
    }

    @GetMapping("/users")
    public ResponseEntity<List<User>> listAllUsers() {
        List<User> users = userService.listAllUsers();
        return ResponseEntity.ok(users);
    }

    @PostMapping("/create-pet")
    public ResponseEntity<List<Pet>> createPet(@RequestBody List<Pet> pets) {
        List<Pet> createdPet = petService.createPet(pets);
        return ResponseEntity.ok(createdPet);
    }

    @PostMapping("/create-user")
    public ResponseEntity<List<User>> createUser(@RequestBody List<User> users) {
        List<User> createdUsers = userService.createUser(users);
        return ResponseEntity.ok(createdUsers);
    }

    @PostMapping("/buy-pet")
    public ResponseEntity<String> buy(@RequestParam Long userId, @RequestParam Long petId) {
        User user = userService.findUserById(userId);
        Pet pet = petService.findPetById(petId);
        if (user == null || pet == null) {
            return ResponseEntity.badRequest().body("User or pet not found.");
        }
        boolean success = petService.buy(user, pet);
        if (success) {
            return ResponseEntity.ok("Pet bought successfully.");
        } else {
            return ResponseEntity.badRequest().body("Failed to buy pet.");
        }
    }
}
