package com.revature.assignforce.service;

import com.revature.assignforce.beans.Project;

import java.util.List;

public interface ProjectServiceProvider {
    void setApiKey(String key);
    Project getProject(String name);
    List<Project> getAllProjects();
    List<Project> getProjectsWithStatus(boolean isActive);
    List<Project> getProjectsWithOwner(String owner);
}
