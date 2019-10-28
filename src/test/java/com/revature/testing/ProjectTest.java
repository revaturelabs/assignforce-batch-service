package com.revature.testing;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.revature.assignforce.beans.Project;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class ProjectTest {
	
	@Configuration
	static class ProjectTestContextConfiguration {
		
	@Bean
	public Project Project() {
		return new Project();
		}
	}
	
	@Test
	public void testEqualsOfSameProjectInstance() {
		Project p1 = new Project();
		
		assertEquals(p1.equals(p1), true);
	}
	
	@Test
	public void testEqualsOfProjectAndNull() {
		Project p1 = new Project();
		
		assertEquals(p1.equals(null), false);
	}
	
	@Test
	public void testEqualsOfProjectAndNonProject() {
		Project p1 = new Project();
		Integer x = new Integer(1);
		
		assertEquals(p1.equals(x), false);
	}
	
	@Test
	public void testEqualsOfProjectAndEquivalentProject() {
		Project p1 = new Project();
		Project p2 = new Project();
		
		p1.setActive(true);
		p1.setDescription("description");
		p1.setId(1);
		p1.setName("name");
		p1.setOwner("owner");
		
		p2.setActive(true);
		p2.setDescription("description");
		p2.setId(1);
		p2.setName("name");
		p2.setOwner("owner");
		
		assertEquals(p1.equals(p2), true);
	}
	
	@Test
	public void testEqualsOfProjectAndNonEquivalentProjectId() {
		Project p1 = new Project();
		Project p2 = new Project();
		
		p1.setActive(true);
		p1.setDescription("description");
		p1.setId(1);
		p1.setName("name");
		p1.setOwner("owner");
		
		p2.setActive(true);
		p2.setDescription("description");
		p2.setId(2);
		p2.setName("name");
		p2.setOwner("owner");
		
		assertEquals(p1.equals(p2), false);
	}
	
	@Test
	public void testEqualsOfProjectAndNonEquivalentProjectActive() {
		Project p1 = new Project();
		Project p2 = new Project();
		
		p1.setActive(true);
		p1.setDescription("description");
		p1.setId(1);
		p1.setName("name");
		p1.setOwner("owner");
		
		p2.setActive(false);
		p2.setDescription("description");
		p2.setId(1);
		p2.setName("name");
		p2.setOwner("owner");
		
		assertEquals(p1.equals(p2), false);
	}
	
	@Test
	public void testEqualsOfProjectAndNonEquivalentProjectName() {
		Project p1 = new Project();
		Project p2 = new Project();
		
		p1.setActive(true);
		p1.setDescription("description");
		p1.setId(1);
		p1.setName("name");
		p1.setOwner("owner");
		
		p2.setActive(true);
		p2.setDescription("description");
		p2.setId(1);
		p2.setName("test");
		p2.setOwner("owner");
		
		assertEquals(p1.equals(p2), false);
	}

	@Test
	public void testEqualsOfProjectAndNonEquivalentProjectDescription() {
		Project p1 = new Project();
		Project p2 = new Project();
		
		p1.setActive(true);
		p1.setDescription("description");
		p1.setId(1);
		p1.setName("name");
		p1.setOwner("owner");
		
		p2.setActive(true);
		p2.setDescription("test");
		p2.setId(1);
		p2.setName("name");
		p2.setOwner("owner");
		
		assertEquals(p1.equals(p2), false);
	}
}
