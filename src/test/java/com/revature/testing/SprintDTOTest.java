package com.revature.testing;

import static org.junit.Assert.assertEquals;

import java.sql.Timestamp;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.revature.assignforce.beans.SprintDTO;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class SprintDTOTest {
	
	private static SprintDTO sprintDTO;
	
	@Configuration
	static class SprintDTOTestContextConfiguration {
		
	@Bean
	public SprintDTO SprintDTO() {
		return new SprintDTO();
		}
	}
	
	@BeforeClass
	public static void setup() {
		sprintDTO = new SprintDTO();
		
		sprintDTO.setUrl("url");
		sprintDTO.setCreatedAt(new Timestamp(1));
		sprintDTO.setUpdatedAt(new Timestamp(1));
		sprintDTO.setColumnsUrl("columns_url");
		sprintDTO.setName("name");
		sprintDTO.setDescription("description");
		sprintDTO.setId(1);
		sprintDTO.setState("state");
	}
	
	@Test
	public void testGetUrl() {
		String url = sprintDTO.getUrl();
		
		assertEquals("url", url);
	}
	
	@Test
	public void testGetCreatedAt() {
		Timestamp ts = new Timestamp(1);
		
		Timestamp createdAt = sprintDTO.getCreatedAt();
		
		assertEquals(ts, createdAt);
	}

	@Test
	public void testGetUpdatedAt() {
		Timestamp ts = new Timestamp(1);
		
		Timestamp updatedAt = sprintDTO.getUpdatedAt();
		
		assertEquals(ts, updatedAt);
	}
	
	@Test
	public void testGetColumnsUrl() {
		String columnsUrl = sprintDTO.getColumnsUrl();
		
		assertEquals("columns_url", columnsUrl);
	}
	
	@Test
	public void testGetName() {
		String name = sprintDTO.getName();
		
		assertEquals("name", name);
	}
	
	@Test
	public void testGetDescription() {
		String description = sprintDTO.getDescription();
		
		assertEquals("description", description);
	}
	
	@Test
	public void testGetId() {
		int id = sprintDTO.getId();
		
		assertEquals(1, id);
	}
	
	@Test
	public void testGetState() {
		String state = sprintDTO.getState();
		
		assertEquals("state", state);
	}
	
	@Test
	public void testEqualsOfSameSprintDTO() {
		assertEquals(sprintDTO.equals(sprintDTO), true);
	}
	
	@Test
	public void testEqualsOfSprintDTOAndNull() {
		assertEquals(sprintDTO.equals(null), false);
	}
	
	@Test
	public void testEqualsOfSprintDTOAndNonSprintDTO() {
		Integer x = new Integer(1);
		
		assertEquals(sprintDTO.equals(x), false);
	}
	
	@Test
	public void testEqualsOfSprintDTOAndEquivalentSprintDTO() {
		SprintDTO sprintDTO2 = new SprintDTO();
		
		sprintDTO2.setUrl("url");
		sprintDTO2.setCreatedAt(new Timestamp(1));
		sprintDTO2.setUpdatedAt(new Timestamp(1));
		sprintDTO2.setColumnsUrl("columns_url");
		sprintDTO2.setName("name");
		sprintDTO2.setDescription("description");
		sprintDTO2.setId(1);
		sprintDTO2.setState("state");
		
		assertEquals(sprintDTO.equals(sprintDTO2), true);
	}
	
	@Test
	public void testEqualsOfSprintDTOAndNonEquivalentSprintDTO() {
		SprintDTO sprintDTO2 = new SprintDTO();
		
		sprintDTO2.setUrl("test");
		sprintDTO2.setCreatedAt(new Timestamp(0));
		sprintDTO2.setUpdatedAt(new Timestamp(0));
		sprintDTO2.setColumnsUrl("test");
		sprintDTO2.setName("test");
		sprintDTO2.setDescription("test");
		sprintDTO2.setId(0);
		sprintDTO2.setState("test");
		
		assertEquals(sprintDTO.equals(sprintDTO2), false);
	}
	
	@Test
	public void testEqualsOfSprintDTOAndNonEquivalentSprintDTOId() {
		SprintDTO sprintDTO2 = new SprintDTO();
		
		sprintDTO2.setUrl("url");
		sprintDTO2.setCreatedAt(new Timestamp(1));
		sprintDTO2.setUpdatedAt(new Timestamp(1));
		sprintDTO2.setColumnsUrl("columns_url");
		sprintDTO2.setName("name");
		sprintDTO2.setDescription("description");
		sprintDTO2.setId(0);
		sprintDTO2.setState("state");
		
		assertEquals(sprintDTO.equals(sprintDTO2), false);
	}
	
	@Test
	public void testEqualsOfSprintDTOAndNonEquivalentSprintDTOUrl() {
		SprintDTO sprintDTO2 = new SprintDTO();
		
		sprintDTO2.setUrl("test");
		sprintDTO2.setCreatedAt(new Timestamp(1));
		sprintDTO2.setUpdatedAt(new Timestamp(1));
		sprintDTO2.setColumnsUrl("columns_url");
		sprintDTO2.setName("name");
		sprintDTO2.setDescription("description");
		sprintDTO2.setId(1);
		sprintDTO2.setState("state");
		
		assertEquals(sprintDTO.equals(sprintDTO2), false);
	}
	
	@Test
	public void testEqualsOfSprintDTOAndNonEquivalentSprintDTOCreatedAt() {
		SprintDTO sprintDTO2 = new SprintDTO();
		
		sprintDTO2.setUrl("url");
		sprintDTO2.setCreatedAt(new Timestamp(0));
		sprintDTO2.setUpdatedAt(new Timestamp(1));
		sprintDTO2.setColumnsUrl("columns_url");
		sprintDTO2.setName("name");
		sprintDTO2.setDescription("description");
		sprintDTO2.setId(1);
		sprintDTO2.setState("state");
		
		assertEquals(sprintDTO.equals(sprintDTO2), false);
	}
	
	@Test
	public void testEqualsOfSprintDTOAndNonEquivalentSprintDTOUpdatedAt() {
		SprintDTO sprintDTO2 = new SprintDTO();
		
		sprintDTO2.setUrl("url");
		sprintDTO2.setCreatedAt(new Timestamp(1));
		sprintDTO2.setUpdatedAt(new Timestamp(0));
		sprintDTO2.setColumnsUrl("columns_url");
		sprintDTO2.setName("name");
		sprintDTO2.setDescription("description");
		sprintDTO2.setId(1);
		sprintDTO2.setState("state");
		
		assertEquals(sprintDTO.equals(sprintDTO2), false);
	}
	
	@Test
	public void testEqualsOfSprintDTOAndNonEquivalentSprintDTOColumnsUrl() {
		SprintDTO sprintDTO2 = new SprintDTO();
		
		sprintDTO2.setUrl("url");
		sprintDTO2.setCreatedAt(new Timestamp(1));
		sprintDTO2.setUpdatedAt(new Timestamp(1));
		sprintDTO2.setColumnsUrl("test");
		sprintDTO2.setName("name");
		sprintDTO2.setDescription("description");
		sprintDTO2.setId(1);
		sprintDTO2.setState("state");
		
		assertEquals(sprintDTO.equals(sprintDTO2), false);
	}
	
	@Test
	public void testEqualsOfSprintDTOAndNonEquivalentSprintDTOName() {
		SprintDTO sprintDTO2 = new SprintDTO();
		
		sprintDTO2.setUrl("url");
		sprintDTO2.setCreatedAt(new Timestamp(1));
		sprintDTO2.setUpdatedAt(new Timestamp(1));
		sprintDTO2.setColumnsUrl("columns_url");
		sprintDTO2.setName("test");
		sprintDTO2.setDescription("description");
		sprintDTO2.setId(1);
		sprintDTO2.setState("state");
		
		assertEquals(sprintDTO.equals(sprintDTO2), false);
	}
	
	@Test
	public void testEqualsOfSprintDTOAndNonEquivalentSprintDTODescription() {
		SprintDTO sprintDTO2 = new SprintDTO();
		
		sprintDTO2.setUrl("url");
		sprintDTO2.setCreatedAt(new Timestamp(1));
		sprintDTO2.setUpdatedAt(new Timestamp(1));
		sprintDTO2.setColumnsUrl("columns_url");
		sprintDTO2.setName("name");
		sprintDTO2.setDescription("test");
		sprintDTO2.setId(1);
		sprintDTO2.setState("state");
		
		assertEquals(sprintDTO.equals(sprintDTO2), false);
	}
	
	@Test
	public void testEqualsOfSprintDTOAndNonEquivalentSprintDTOState() {
		SprintDTO sprintDTO2 = new SprintDTO();
		
		sprintDTO2.setUrl("url");
		sprintDTO2.setCreatedAt(new Timestamp(1));
		sprintDTO2.setUpdatedAt(new Timestamp(1));
		sprintDTO2.setColumnsUrl("columns_url");
		sprintDTO2.setName("name");
		sprintDTO2.setDescription("description");
		sprintDTO2.setId(1);
		sprintDTO2.setState("test");
		
		assertEquals(sprintDTO.equals(sprintDTO2), false);
	}
	
	@Test
	public void testToString() {
		String expected = "SprintDTO{" +
                "id=" + 1 +
                ", url='" + "url" + '\'' +
                ", createdAt=" + new Timestamp(1) +
                ", updatedAt=" + new Timestamp(1) +
                ", columnsUrl='" + "columns_url" + '\'' +
                ", name='" + "name" + '\'' +
                ", description='" + "description" + '\'' +
                ", state='" + "state" + '\'' +
                '}';
		
		assertEquals(expected, sprintDTO.toString());
	}
}
