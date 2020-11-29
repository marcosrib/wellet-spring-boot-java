package com.wallet.repository;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Optional;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


import com.wallet.entity.User;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserRepositoryTest {

	private static final String EMAIL = "email@test.com";
	@Autowired
	UserRepository repository;

	@BeforeEach
	public void setUp() {
		User u = new User();
		u.setName("set up user");
		u.setPassword("Senha123");
		u.setEmail(EMAIL);
		System.err.println("ff");
		repository.save(u);
	}
	
	@AfterEach
	public void tearDown() {
		 repository.deleteAll();
	}
	
   @Test
	public void TestSave() {
		User u = new User();
		u.setName("Teste");
		u.setPassword("12345");
		u.setEmail("teste@teste.com");
		User response = repository.save(u);

		assertNotNull(response);
	}
	
	@Test
	public void testFindByEmail() {
		Optional<User> response = repository.findByEmailEquals(EMAIL);
		System.err.println(response);
		assertTrue(response.isPresent());
		assertEquals(response.get().getEmail(), EMAIL);
	}
}
