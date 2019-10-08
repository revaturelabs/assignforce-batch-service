package com.revature.testing;

import com.revature.assignforce.beans.Project;
import com.revature.assignforce.beans.ProjectDTO;
import com.revature.assignforce.controllers.ProjectController;
import com.revature.assignforce.service.ProjectServiceProvider;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RunWith(MockitoJUnitRunner.class)
public class ProjectControllerTest {
    @Mock
    ProjectServiceProvider projectServiceProvider;

    @InjectMocks
    ProjectController controller = new ProjectController();

    private List<Project> projectMocks = new ArrayList<>();

    @Before
    public void beforeEach() {
        controller.setHostName("localhost");
        projectMocks = new ArrayList<>();
        Project p = new Project();
        p.setId(1);
        p.setActive(true);
        p.setName("Assignforce");
        p.setDescription("Manage batch resources");
        p.setOwner("august.duet@revature.com");

        projectMocks.add(p);

        p = new Project();
        p.setId(2);
        p.setActive(true);
        p.setName("Caliber");
        p.setDescription("Assess associates");
        p.setOwner("quintin.donnelly@revature.com");

        projectMocks.add(p);

        p = new Project();
        p.setId(3);
        p.setActive(false);
        p.setName("Janus");
        p.setDescription("Revature MSA");
        p.setOwner("blake.kruppa@revature.com");

        projectMocks.add(p);
    }

    @Test
    public void shouldReturnAllProjectDTOs() {
        Mockito
                .when(projectServiceProvider.getAllProjects())
                .thenReturn(projectMocks);
    	
    	projectServiceProvider.getAllProjects();
        
        ResponseEntity<List<ProjectDTO>> dtos = controller.getAllProjects(null);
        System.out.println(dtos.getBody());
        
        Assert.assertNotEquals(dtos, null);
        
        //Assert.assertTrue(true);
    }
    
    @Test
    public void shouldReturn400BadRequest() {
		Mockito.when(projectServiceProvider.getAllProjects()).thenReturn(projectMocks);

		projectServiceProvider.getAllProjects();

		ResponseEntity<List<ProjectDTO>> dtos = controller.getAllProjects("test");

		Assert.assertEquals(400, dtos.getStatusCodeValue());
    }

    @Test
    public void shouldReturn404OnNotFound() {
        Mockito
                .when(projectServiceProvider.getProject("NotAProject"))
                .thenReturn(null);
        ResponseEntity<ProjectDTO> dto = controller.getProjectByName("NotAProject");
        Assert.assertNotNull("Null Response", dto);
        Assert.assertEquals("Unexpected Status", HttpStatus.NOT_FOUND, dto.getStatusCode());
        Assert.assertNull("Unexpected DTO return",dto.getBody());
    }

    @Test
    public void shouldReturnProject() {
        Mockito
                .when(projectServiceProvider.getProject("Assignforce"))
                .thenReturn(projectMocks.get(0));
        ResponseEntity<ProjectDTO> dto = controller.getProjectByName("Assignforce");
        Assert.assertNotNull("Null Response", dto);
        Assert.assertEquals("Unexpected Status", HttpStatus.OK, dto.getStatusCode());
        Assert.assertNotNull("Unexpected DTO return",dto.getBody());
        Assert.assertEquals("Unexpected DTO", toDTO(projectMocks.get(0)), dto.getBody());
    }

    @Test
    public void shouldReturnActiveProjects() {
        Mockito
                .when(projectServiceProvider.getProjectsWithStatus(true))
                .thenReturn(filterProjectsWithStatus(true));

        ResponseEntity<List<ProjectDTO>> dtos = controller.getAllProjects("active");
        Assert.assertNotNull("Null response", dtos.getBody());
        Assert.assertEquals("Unexpected status", HttpStatus.OK, dtos.getStatusCode());
        Assert.assertArrayEquals("Unexpected list",
                filterProjectsWithStatus(true).stream()
                        .map(this::toDTO).toArray(),
                dtos.getBody().toArray());
    }

    @Test
    public void shouldReturnInactiveProjects() {
        Mockito
                .when(projectServiceProvider.getProjectsWithStatus(false))
                .thenReturn(filterProjectsWithStatus(false));

        ResponseEntity<List<ProjectDTO>> dtos = controller.getAllProjects("inactive");
        Assert.assertNotNull("Null response", dtos.getBody());
        Assert.assertEquals("Unexpected status", HttpStatus.OK, dtos.getStatusCode());
        Assert.assertArrayEquals("Unexpected list",
                filterProjectsWithStatus(false).stream()
                        .map(this::toDTO).toArray(),
                dtos.getBody().toArray());
    }

    @Test
    public void should404OnInvalidId() {
        Mockito
                .when(projectServiceProvider.getProjectById(1))
                .thenReturn(null);
        ResponseEntity ret = controller.updateProject(1, null);
        Assert.assertEquals("Didn't receive a 404",
                HttpStatus.NOT_FOUND,
                ret.getStatusCode());
    }

    @Test
    public void shouldNotFailOnGoodInput() {
        Mockito
                .when(projectServiceProvider.getProjectById(1))
                .thenReturn(projectMocks.get(0));
        ResponseEntity ret = controller.updateProject(1, toDTO(projectMocks.get(0)));
        Assert.assertEquals("Unexpected status returned",
                HttpStatus.NO_CONTENT,
                ret.getStatusCode());
    }

    private ProjectDTO toDTO(Project p) {
        ProjectDTO dto = new ProjectDTO();
        dto.setName(p.getName());
        dto.setUrl(String.format("%s/batch-service/p/%s", "localhost", p.getName()));
        dto.setDescription(p.getDescription());
        dto.setId(p.getId());
        dto.setActive(p.isActive());
        dto.setOwner(p.getOwner());

        return dto;
    }

    private List<Project> filterProjectsWithStatus(boolean isActive) {
        return projectMocks.stream()
                .filter(project -> project.isActive() == isActive)
                .collect(Collectors.toList());
    }
}
