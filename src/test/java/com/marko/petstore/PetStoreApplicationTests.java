package com.marko.petstore;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

import java.util.Random;

public class PetStoreApplicationTests {

	private WebDriver driver;

	@BeforeEach
	public void setUp() {
		driver = new HtmlUnitDriver();
	}

	@AfterEach
	public void tearDown() {
		if (driver != null) {
			driver.quit();
		}
	}

	@Test
	public void testCreateUsers() {
		driver.get("http://localhost:1111/create-user");
		for (int i = 0; i < 10; i++) {
			createUser();
		}
	}

	@Test
	public void testCreatePets() {
		driver.get("http://localhost:1111/create-pet");
		for (int i = 0; i < 20; i++) {
			createPet();
		}
	}

	private void createUser() {
		WebElement firstNameInput = driver.findElement(By.id("firstName"));
		WebElement lastNameInput = driver.findElement(By.id("lastName"));
		WebElement emailInput = driver.findElement(By.id("email"));
		WebElement budgetInput = driver.findElement(By.id("budget"));

		Random random = new Random();
		String firstName = "User" + random.nextInt(100);
		String lastName = "LastName" + random.nextInt(100);
		String email = "user" + random.nextInt(100) + "@example.com";
		int budget = random.nextInt(1000) + 100;

		firstNameInput.sendKeys(firstName);
		lastNameInput.sendKeys(lastName);
		emailInput.sendKeys(email);
		budgetInput.sendKeys(String.valueOf(budget));

		WebElement submitButton = driver.findElement(By.id("submit"));
		submitButton.click();
	}

	private void createPet() {
		WebElement nameInput = driver.findElement(By.id("name"));
		WebElement typeInput = driver.findElement(By.id("type"));
		WebElement descriptionInput = driver.findElement(By.id("description"));
		WebElement dobInput = driver.findElement(By.id("dob"));
		WebElement priceInput = driver.findElement(By.id("price"));

		Random random = new Random();
		String name = "Pet" + random.nextInt(100);
		String type = random.nextBoolean() ? "Cat" : "Dog";
		String description = "Description of " + name;
		String dob = "2022-01-01";
		int price = random.nextInt(10) + 1;

		nameInput.sendKeys(name);
		typeInput.sendKeys(type);
		descriptionInput.sendKeys(description);
		dobInput.sendKeys(dob);
		priceInput.sendKeys(String.valueOf(price));

		WebElement submitButton = driver.findElement(By.id("submit"));
		submitButton.click();
	}
}