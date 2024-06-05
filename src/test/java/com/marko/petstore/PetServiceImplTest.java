package com.marko.petstore;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.marko.petstore.model.Cat;
import com.marko.petstore.model.Dog;
import com.marko.petstore.model.Pet;
import com.marko.petstore.model.User;
import com.marko.petstore.repository.PetRepository;
import com.marko.petstore.repository.UserRepository;
import com.marko.petstore.service.impl.PetServiceImpl;
import com.marko.petstore.service.impl.UserServiceImpl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PetServiceImplTest {

	private PetRepository petRepository;
	private UserRepository userRepository;
	private PetServiceImpl petService;
	private UserServiceImpl userService;

	@BeforeEach
	public void setUp() {
		petRepository = mock(PetRepository.class);
		userRepository = mock(UserRepository.class);
		petService = new PetServiceImpl(petRepository);
		userService = new UserServiceImpl(userRepository);
	}

	@Test
	public void testFindPetById() {
		Long id = 1L;
		Pet expectedPet = new Cat(new User("Marko", "Stojkovski", "markostojkovski5@gmail.com", 100), "Tom", "Friendly cat", LocalDate.now(), 2);
		when(petRepository.findById(id)).thenReturn(Optional.of(expectedPet));

		Pet actualPet = petService.findPetById(id);

		assertEquals(expectedPet, actualPet);
	}

	@Test
	public void testListAllPets() {
		List<Pet> expectedPets = new ArrayList<>();
		expectedPets.add(new Cat(new User("Marko", "Stojkovski", "markostojkovski5@gmail.com", 100), "Tom", "Friendly cat", LocalDate.now(), 2));
		expectedPets.add(new Dog(new User("Scopisto", "Technology", "scopisto@technology.com", 200), "Max", "Playful dog", LocalDate.now(), 3, 10));
		when(petRepository.findAll()).thenReturn(expectedPets);

		List<Pet> actualPets = petService.listAllPets();

		assertEquals(expectedPets, actualPets);
	}

	@Test
	public void testBuyPet() {
		Long userId = 1L;
		Long petId = 1L;
		User user = new User("Marko", "Stojkovski", "markostojkovski5@gmail.com", 100);
		Pet pet = new Cat(null, "Tom", "Friendly cat", LocalDate.now(), 2);
		when(petRepository.findById(petId)).thenReturn(Optional.of(pet));

		boolean success = petService.buy(user, pet);

		assertTrue(success);
		assertEquals(user, pet.getOwner());
		verify(petRepository, times(1)).save(pet);
	}

	@Test
	public void testCreatePet() {
		List<Pet> pets = new ArrayList<>();
		pets.add(new Cat(null, "Tom", "Friendly cat", LocalDate.now(), 2));
		pets.add(new Dog(null, "Max", "Playful dog", LocalDate.now(), 5, 10));
		when(petRepository.save(any())).thenAnswer(invocation -> invocation.getArgument(0));

		List<Pet> createdPets = petService.createPet(pets);

		assertEquals(pets.size(), createdPets.size());
		for (int i = 0; i < pets.size(); i++) {
			assertEquals(pets.get(i), createdPets.get(i));
		}
		verify(petRepository, times(pets.size())).save(any());
	}

	@Test
	public void testFindUserById() {
		Long id = 1L;
		User expectedUser = new User("Marko", "Stojkovski", "markostojkovski5@gmail.com", 100);
		when(userRepository.findById(id)).thenReturn(Optional.of(expectedUser));

		User actualUser = userService.findUserById(id);

		assertEquals(expectedUser, actualUser);
	}

	@Test
	public void testListAllUsers() {
		List<User> expectedUsers = new ArrayList<>();
		expectedUsers.add(new User("Marko", "Stojkovski", "markostojkovski5@gmail.com", 100));
		expectedUsers.add(new User("Scopisto", "Technology", "scopisto@technology.com", 200));
		when(userRepository.findAll()).thenReturn(expectedUsers);

		List<User> actualUsers = userService.listAllUsers();

		assertEquals(expectedUsers, actualUsers);
	}

	@Test
	public void testCreateUser() {
		List<User> users = new ArrayList<>();
		users.add(new User("Marko", "Stojkovski", "markostojkovski5@gmail.com", 100));
		users.add(new User("Scopisto", "Technology", "scopisto@technology.com", 200));
		when(userRepository.saveAll(any())).thenAnswer(invocation -> invocation.getArgument(0));

		List<User> createdUsers = userService.createUser(users);

		assertEquals(users.size(), createdUsers.size());
		for (int i = 0; i < users.size(); i++) {
			assertEquals(users.get(i), createdUsers.get(i));
		}
		verify(userRepository, times(1)).saveAll(any());
	}
}