package com.revature.assignforce.controllers;

import com.revature.assignforce.beans.Project;
import com.revature.assignforce.beans.ProjectDTO;
import com.revature.assignforce.service.ProjectServiceProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("p")
public class ProjectController {

    @Value("${spring.cloud.inetutils.default-hostname}")
    private String hostName;

    private ProjectServiceProvider projectServiceProvider;

    @Autowired
    public void setProjectServiceProvider(ProjectServiceProvider projectServiceImpl) {
        this.projectServiceProvider = projectServiceImpl;
    }

    public void setHostName(String hostName) {
        this.hostName = hostName;
    }

    @GetMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ProjectDTO>> getAllProjects(@RequestParam(value = "status",required = false)String status) {
        List<ProjectDTO> projectDTOS = new ArrayList<>();
        List<Project> projects;

        if(status != null) {
            if(status.equals("active") || status.equals("inactive")) {
                projects = projectServiceProvider.getProjectsWithStatus(status.equals("active"));

            } else {
                return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
            }

        } else {
            projects = projectServiceProvider.getAllProjects();
        }


        projects.forEach(project -> projectDTOS.add(toDTO(project)));
        return new ResponseEntity<>(projectDTOS, getJsonHeader(), HttpStatus.OK);
    }

    @GetMapping(value="{name}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ProjectDTO> getProjectByName(@PathVariable String name) {
        Project p = projectServiceProvider.getProject(name);
        ResponseEntity<ProjectDTO> responseEntity;

        if(p == null) {
            responseEntity = new ResponseEntity<>((ProjectDTO)null, HttpStatus.NOT_FOUND);
        } else {
            responseEntity = new ResponseEntity<>(toDTO(p), getJsonHeader(), HttpStatus.OK);
        }

        return responseEntity;
    }

    @PutMapping("{id}")
    public ResponseEntity updateProject(@PathVariable int id, @RequestBody ProjectDTO d) {
      Project p = this.projectServiceProvider.getProjectById(id);

      if(p == null) {
          return new ResponseEntity(null, HttpStatus.NOT_FOUND);
      } else {
          this.projectServiceProvider.updateProject(mergeDTO(d));
      }

      return new ResponseEntity(null, HttpStatus.NO_CONTENT);
    }

    private HttpHeaders getJsonHeader() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        return headers;
    }

    private ProjectDTO toDTO(Project p) {
        ProjectDTO dto = new ProjectDTO();
        dto.setName(p.getName());
        dto.setUrl(String.format("%s/batch-service/p/%s", hostName, p.getName()));
        dto.setDescription(p.getDescription());
        dto.setId(p.getId());
        dto.setActive(p.isActive());
        dto.setOwner(p.getOwner());

        return dto;
    }

    private Project mergeDTO(ProjectDTO d) {
        Project p = new Project();
        p.setId(d.getId());
        p.setOwner(d.getOwner());
        p.setDescription(d.getDescription());
        p.setName(d.getName());
        p.setActive(d.isActive());

        return p;
    }
}
