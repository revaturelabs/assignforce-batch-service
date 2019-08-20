package com.revature.assignforce.service;

import com.revature.assignforce.beans.Project;
import com.revature.assignforce.repos.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class AsanaProjectServiceProvider implements ProjectServiceProvider {
    private String apiKey;
    private ProjectRepository projectRepository;

    public AsanaProjectServiceProvider() {}

    public void setApiKey(String key) {
        this.apiKey = key;
    }

    @Autowired
    public void setProjectRepository(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    @Override
    public Project getProject(String name) {
        return null;
    }

    @Override
    public List<Project> getAllProjects() {
        return null;
    }

    @Override
    public List<Project> getProjectsWithStatus(boolean isActive) {
        return null;
    }

    @Override
    public List<Project> getProjectsWithOwner(String Owner) {
        return null;
    }
}
