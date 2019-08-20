package com.revature.assignforce.repos;

import com.revature.assignforce.beans.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Integer> {
    Project findByName(String name);
    List<Project> findAllByActiveEquals(boolean isActive);
    List<Project> findAllByOwner(String owner);
}
