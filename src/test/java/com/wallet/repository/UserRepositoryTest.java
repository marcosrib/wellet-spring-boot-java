package com.wallet.repository;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Optional;

import org.junit.jupiter.api.AfterEach;
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
	private UserRepository repository;

	@BeforeEach
	public void setUp() {
		User u = new User();
		u.setName("set up user");
		u.setPassword("Senha123");
		u.setEmail(EMAIL);
		repository.save(u);
		System.err.println("passou");
	}
	
	@AfterEach
	public void tearDown() {
		 repository.deleteAll();
	}
	
  
	public void testSave() {
		User u = new User();
		u.setName("Teste");
		u.setPassword("12345");
		System.out.println(",l,l");
		u.setEmail("teste@teste.com");
		User response = repository.save(u);

		assertNotNull(response);
	}
	
	public void testFindByEmail() {
		Optional<User> response = repository.findByEmailEquals(EMAIL);
		System.err.println(response);
		assertTrue(response.isPresent());
		assertEquals(response.get().getEmail(), EMAIL);
	}
}
