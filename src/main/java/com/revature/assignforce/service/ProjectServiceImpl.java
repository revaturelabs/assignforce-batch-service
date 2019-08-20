package com.revature.assignforce.service;

import com.revature.assignforce.beans.Project;
import com.revature.assignforce.repos.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class ProjectServiceImpl implements ProjectServiceProvider {

    private ProjectRepository projectRepository;

    public ProjectServiceImpl() {}


    @Autowired
    public void setProjectRepository(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }


    @Override
    public Project getProjectById(int id) {
        return this.projectRepository.getOne(id);
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
        this.projectRepository.save(p);
    }
}
