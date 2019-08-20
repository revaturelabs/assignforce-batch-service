package com.revature.assignforce.service;

import com.revature.assignforce.beans.Project;
import com.revature.assignforce.repos.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
@Transactional
public class GitHubProjectServiceProvider implements ProjectServiceProvider {

    private String apiKey;
    private ProjectRepository projectRepository;
    private RestTemplate restTemplate;
    private String apiUrl = "https://api.github.com/repos/revaturelabs/";

    public GitHubProjectServiceProvider() {}

    public void setApiKey(String key){
        this.apiKey = key;
    }

    @Autowired
    public void setProjectRepository(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    @Autowired
    public void setRestTemplate(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public Project getProjectById(int id) {
        return null;
    }

    @Override
    public Project getProject(String name) {
        return projectRepository.findByName(name);
    }

    @Override
    public List<Project> getAllProjects() {
        return projectRepository.findAll();
    }

    @Override
    public List<Project> getProjectsWithStatus(boolean isActive) {
        return projectRepository.findAllByActiveEquals(isActive);
    }

    @Override
    public List<Project> getProjectsWithOwner(String owner) {
        return projectRepository.findAllByOwner(owner);
    }

    @Override
    public void updateProject(Project p) {

    }
}
