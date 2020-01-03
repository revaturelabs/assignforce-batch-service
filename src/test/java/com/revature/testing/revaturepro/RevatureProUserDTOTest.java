package com.revature.testing.revaturepro;

import com.revature.assignforce.beans.revaturepro.RevatureProUserDTO;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class RevatureProUserDTOTest {
	
	private static RevatureProUserDTO revatureProUserDTO;
	
	@Configuration
	static class RevatureProUserDTOTestContextConfiguration {
		
	@Bean
	public RevatureProUserDTO RevatureProUserDTO() {
		return new RevatureProUserDTO();
		}

	}
	
	@BeforeClass
	public static void setup() {
		// assign revatureProUserDTO to new instance of RevatureProUserDTO
		revatureProUserDTO = new RevatureProUserDTO();
		revatureProUserDTO.setUsername("test");
		revatureProUserDTO.setPassword("password");
		revatureProUserDTO.setToken("Token");

	}
	
	@Test
	public void testGetUsername() {

		String username = this.revatureProUserDTO.getUsername();
		
		assertEquals("test", username);
	}

	@Test
	public void testGetPassword() {
		String pw = this.revatureProUserDTO.getPassword();

		assertEquals("password", pw);
	}

	@Test
	public void testGetToken() {
		String token = this.revatureProUserDTO.getToken();

		assertEquals("Token", token);
	}
	
	@Test
	public void testEqualsOfSameRevatureProUserDTO() {
		assertEquals(this.revatureProUserDTO.equals(this.revatureProUserDTO), true);
	}
	
	@Test
	public void testEqualsOfRevatureProUserDTOAndNull() {
		assertEquals(this.revatureProUserDTO.equals(null), false);
	}
	
	@Test
	public void testEqualsOfProjectDTOAndNonProjectDTO() {
		Integer x = new Integer(1);
		
		assertEquals(this.revatureProUserDTO.equals(x), false);
	}
	
	@Test
	public void testEqualsOfRPUDTOAndEquivalentRPUDTO() {
		RevatureProUserDTO revatureProUserDTO2 = new RevatureProUserDTO();
		revatureProUserDTO2.setUsername("test");
		revatureProUserDTO2.setPassword("password");
		revatureProUserDTO2.setToken("Token");

		assert(revatureProUserDTO.getUsername() == revatureProUserDTO2.getUsername());
	}
	
	@Test
	public void testEqualsOfProjectDTOAndNonEquivalentProjectDTO() {
		RevatureProUserDTO revatureProUserDTO3 = new RevatureProUserDTO();
		revatureProUserDTO3.setUsername("test500");
		revatureProUserDTO3.setPassword("password500");
		revatureProUserDTO3.setToken("Token500");

		assertEquals(this.revatureProUserDTO.equals(revatureProUserDTO3), false);
	}

	@Test
	public void testEqualsOfProjectDTOAndNonEquivalentProjectDTOName() {
		RevatureProUserDTO revatureProUserDTO4 = new RevatureProUserDTO();
		revatureProUserDTO4.setUsername("test600");
		revatureProUserDTO4.setPassword("password");
		revatureProUserDTO4.setToken("Token");
		assertEquals(this.revatureProUserDTO.equals(revatureProUserDTO4), false);
	}
}
