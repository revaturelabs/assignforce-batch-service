package com.revature.assignforce.service;

import com.revature.assignforce.beans.Project;

import java.util.List;

public interface ProjectServiceProvider {
    Project getProjectById(int id);
    Project getProject(String name);
    List<Project> getAllProjects();
    List<Project> getProjectsWithStatus(boolean isActive);
    List<Project> getProjectsWithOwner(String owner);
    void updateProject(Project p);
}
