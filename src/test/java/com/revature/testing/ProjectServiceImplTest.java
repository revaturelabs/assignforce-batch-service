package com.revature.testing;

import com.revature.assignforce.beans.Project;
import com.revature.assignforce.repos.ProjectRepository;
import com.revature.assignforce.service.ProjectServiceImpl;
import com.revature.assignforce.service.ProjectServiceProvider;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
@Profile("local-dev")
public class ProjectServiceImplTest {

    @Mock
    RestTemplate restTemplate;

    @Mock
    ProjectRepository projectRepository;

    @InjectMocks
    ProjectServiceProvider projectServiceProvider = new ProjectServiceImpl();

    private List<Project> projectMocks;

    @Before
    public void beforeEach() {
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
    public void shouldCreate() {}

    @Test
    public void shouldReturnAllProjects() {
        Mockito
                .when(projectRepository.findAll())
                .thenReturn(projectMocks);
        Assert.assertNotNull("Null list", projectServiceProvider.getAllProjects());
        Assert.assertArrayEquals("Unexpected project list",
                projectMocks.toArray(),
                projectServiceProvider.getAllProjects().toArray());
    }

    @Test
    public void shouldReturnValidProject() {
        Mockito
                .when(projectRepository.findByName("Assignforce"))
                .thenReturn(projectMocks.get(0));
        Assert.assertEquals("Unexpected project value",
                projectMocks.get(0),
                projectServiceProvider.getProject("Assignforce"));
    }

    @Test
    public void shouldReturnAllActiveProjects() {
        Mockito
                .when(projectRepository.findAllByActiveEquals(true))
                .thenReturn(filterActiveProjects(true));
        Assert.assertNotNull("Null list", projectServiceProvider.getProjectsWithStatus(true));
        Assert.assertArrayEquals("Unexpected project list for active projects",
                new Project[]{projectMocks.get(0), projectMocks.get(1)},
                projectServiceProvider.getProjectsWithStatus(true).toArray());
    }

    @Test
    public void shouldReturnAllInactiveProjects() {
        Mockito
                .when(projectRepository.findAllByActiveEquals(false))
                .thenReturn(filterActiveProjects(false));
        Assert.assertNotNull("Null list", projectServiceProvider.getProjectsWithStatus(false));
        Assert.assertArrayEquals("Unexpected project list for active projects",
                new Project[]{projectMocks.get(2)},
                projectServiceProvider.getProjectsWithStatus(false).toArray());
    }

    @Test
    public void shouldReturnOwnerProjects() {
        Mockito
                .when(projectRepository.findAllByOwner("august.duet@revature.com"))
                .thenReturn(filterOwnerProjects("august.duet@revature.com"));
        Mockito
                .when(projectRepository.findAllByOwner("quintin.donnelly@revature.com"))
                .thenReturn(filterOwnerProjects("quintin.donnelly@revature.com"));
        Mockito
                .when(projectRepository.findAllByOwner("blake.kruppa@revature.com"))
                .thenReturn(filterOwnerProjects("blake.kruppa@revature.com"));

        Assert.assertNotNull("Null list",
                projectServiceProvider.getProjectsWithOwner("august.duet@revature.com"));
        Assert.assertNotNull("Null list",
                projectServiceProvider.getProjectsWithOwner("quintin.donnelly@revature.com"));
        Assert.assertNotNull("Null list",
                projectServiceProvider.getProjectsWithOwner("blake.kruppa@revature.com"));

        Assert.assertArrayEquals("Unexpected project list",
                filterOwnerProjects("august.duet@revature.com").toArray(),
                projectServiceProvider.getProjectsWithOwner("august.duet@revature.com").toArray());
        Assert.assertArrayEquals("Unexpected project list",
                filterOwnerProjects("quintin.donnelly@revature.com").toArray(),
                projectServiceProvider.getProjectsWithOwner("quintin.donnelly@revature.com").toArray());
        Assert.assertArrayEquals("Unexpected project list",
                filterOwnerProjects("blake.kruppa@revature.com").toArray(),
                projectServiceProvider.getProjectsWithOwner("blake.kruppa@revature.com").toArray());
    }

    @Test
    public void shouldReturnNullOnNonExistentId() {
        Mockito
                .when(projectRepository.getOne(10))
                .thenReturn(null);
        Assert.assertNull("Should be null", projectServiceProvider.getProjectById(10));
    }

    @Test
    public void shouldReturnProjectExistentId() {
        Mockito
                .when(projectRepository.getOne(1))
                .thenReturn(projectMocks.get(0));
        Assert.assertEquals("Unexpected Project",
                projectMocks.get(0),
                projectServiceProvider.getProjectById(1));
    }
    
    @Test
    public void shouldUpdateProject() {
    	projectMocks.get(0).setName("TEST");
    	
    	projectServiceProvider.updateProject(projectMocks.get(0));
    	
        Mockito
        		.when(projectRepository.getOne(1))
        		.thenReturn(projectMocks.get(0));
        
        Assert.assertEquals("Unexpected Project",
        		projectServiceProvider.getProjectById(1).getName(),
        		"TEST");
    }

    private List<Project> filterActiveProjects(boolean isActive) {
        return projectMocks.stream()
                .filter(project -> project.isActive() == isActive)
                .collect(Collectors.toList());
    }

    private List<Project> filterOwnerProjects(String owner) {
        return projectMocks.stream()
                .filter(project -> project.getOwner().equals(owner))
                .collect(Collectors.toList());
    }
}
