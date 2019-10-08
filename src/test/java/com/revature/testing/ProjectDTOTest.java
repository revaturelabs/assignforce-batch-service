package com.revature.testing;

import static org.junit.Assert.assertEquals;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.revature.assignforce.beans.ProjectDTO;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class ProjectDTOTest {
	
	private static ProjectDTO projectDTO;
	
	@Configuration
	static class ProjectDTOTestContextConfiguration {
		
	@Bean
	public ProjectDTO ProjectDTO() {
		return new ProjectDTO();
		}
	}
	
	@BeforeClass
	public static void setup() {
		projectDTO = new ProjectDTO();
		
		projectDTO.setActive(true);
		projectDTO.setDescription("description");
		projectDTO.setId(1);
		projectDTO.setName("name");
		projectDTO.setOwner("owner");
		projectDTO.setSprints(new String[] {"sprints"});
		projectDTO.setUrl("url");
	}
	
	@Test
	public void testGetUrl() {
		String url = projectDTO.getUrl();
		
		assertEquals("url", url);
	}
	
	@Test
	public void testSetGetSprints() {
		String sprints[] = {"sprint1"};
		
		projectDTO.setSprints(sprints);
		
		String sprint = projectDTO.getSprints()[0];
		
		assertEquals(sprints[0], sprint);
	}
	
	@Test
	public void testEqualsOfSameProjectDTOInstance() {
		assertEquals(projectDTO.equals(projectDTO), true);
	}
	
	@Test
	public void testEqualsOfProjectDTOAndNull() {
		assertEquals(projectDTO.equals(null), false);
	}
	
	@Test
	public void testEqualsOfProjectDTOAndNonProjectDTO() {
		Integer x = new Integer(1);
		
		assertEquals(projectDTO.equals(x), false);
	}
	
	@Test
	public void testEqualsOfProjectDTOAndEquivalentProjectDTO() {
		ProjectDTO projectDTO2 = new ProjectDTO();
		
		projectDTO2.setActive(true);
		projectDTO2.setDescription("description");
		projectDTO2.setId(1);
		projectDTO2.setName("name");
		projectDTO2.setOwner("owner");
		projectDTO2.setSprints(new String[] {"sprints"});
		projectDTO2.setUrl("url");
		
		assertEquals(projectDTO.equals(projectDTO2), true);
	}
	
	@Test
	public void testEqualsOfProjectDTOAndNonEquivalentProjectDTO() {
		ProjectDTO projectDTO2 = new ProjectDTO();
		
		projectDTO2.setActive(false);
		projectDTO2.setDescription("test");
		projectDTO2.setId(2);
		projectDTO2.setName("test");
		projectDTO2.setOwner("test");
		projectDTO2.setSprints(new String[] {"test"});
		projectDTO2.setUrl("test");
		
		assertEquals(projectDTO.equals(projectDTO2), false);
	}

	@Test
	public void testEqualsOfProjectDTOAndNonEquivalentProjectDTOName() {
		ProjectDTO projectDTO2 = new ProjectDTO();
		
		projectDTO2.setActive(true);
		projectDTO2.setDescription("description");
		projectDTO2.setId(1);
		projectDTO2.setName("test");
		projectDTO2.setOwner("owner");
		projectDTO2.setSprints(new String[] {"sprints"});
		projectDTO2.setUrl("url");
		
		assertEquals(projectDTO.equals(projectDTO2), false);
	}
	
	@Test
	public void testEqualsOfProjectDTOAndNonEquivalentProjectDTOOwner() {
		ProjectDTO projectDTO2 = new ProjectDTO();
		
		projectDTO2.setActive(true);
		projectDTO2.setDescription("description");
		projectDTO2.setId(1);
		projectDTO2.setName("name");
		projectDTO2.setOwner("test");
		projectDTO2.setSprints(new String[] {"sprints"});
		projectDTO2.setUrl("url");
		
		assertEquals(projectDTO.equals(projectDTO2), false);
	}
	
	@Test
	public void testEqualsOfProjectDTOAndNonEquivalentProjectDTOUrl() {
		ProjectDTO projectDTO2 = new ProjectDTO();
		
		projectDTO2.setActive(true);
		projectDTO2.setDescription("description");
		projectDTO2.setId(1);
		projectDTO2.setName("name");
		projectDTO2.setOwner("owner");
		projectDTO2.setSprints(new String[] {"sprints"});
		projectDTO2.setUrl("test");
		
		assertEquals(projectDTO.equals(projectDTO2), false);
	}
	
	@Test
	public void testEqualsOfProjectDTOAndNonEquivalentProjectDTODescription() {
		ProjectDTO projectDTO2 = new ProjectDTO();
		
		projectDTO2.setActive(true);
		projectDTO2.setDescription("test");
		projectDTO2.setId(1);
		projectDTO2.setName("name");
		projectDTO2.setOwner("owner");
		projectDTO2.setSprints(new String[] {"sprints"});
		projectDTO2.setUrl("url");
		
		assertEquals(projectDTO.equals(projectDTO2), false);
	}
	
	@Test
	public void testEqualsOfProjectDTOAndNonEquivalentProjectDTOSprints() {
		ProjectDTO projectDTO2 = new ProjectDTO();
		
		projectDTO2.setActive(true);
		projectDTO2.setDescription("description");
		projectDTO2.setId(1);
		projectDTO2.setName("name");
		projectDTO2.setOwner("owner");
		projectDTO2.setSprints(new String[] {"test"});
		projectDTO2.setUrl("url");
		
		assertEquals(projectDTO.equals(projectDTO2), false);
	}
}
