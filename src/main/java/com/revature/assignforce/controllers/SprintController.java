package com.revature.assignforce.controllers;

import com.revature.assignforce.beans.SprintDTO;
import com.revature.assignforce.service.IssuesServiceProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("p/{name}")
public class SprintController {

    @Value("${spring.cloud.inetutils.default-hostname}")
    String hostName;

    private IssuesServiceProvider issuesServiceProvider;

    public SprintController() {/*default*/}

    @Autowired
    public void setIssuesServiceProvider(IssuesServiceProvider issuesServiceProvider) {
        this.issuesServiceProvider = issuesServiceProvider;
    }

    @GetMapping("/sprints")
    public ResponseEntity<List<SprintDTO>> getProjectSprints(@PathVariable String name) {
        List<SprintDTO> sprints = issuesServiceProvider.getNativeApiSprints(name);

        if(sprints == null || sprints.isEmpty()) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        } else {
            for(SprintDTO sprint : sprints) {
                sprint.setUrl(transformUrl(name, "sprint/" + sprint.getId()));
                sprint.setColumnsUrl(transformUrl(name,
                        String.format("sprint/%d/columns", sprint.getId())));
            }
            return new ResponseEntity<>(sprints, HttpStatus.OK);
        }
    }

    @GetMapping("sprint/{id}")
    public ResponseEntity<SprintDTO> getSprintById(@PathVariable int id) {
        return new ResponseEntity<>(null, HttpStatus.OK);
    }

    @GetMapping("sprint/{id}/columns")
    public ResponseEntity getSprintColumns(@PathVariable int id) {
        return null;
    }

    private String transformUrl(String projectName, String target) {
        return String.format("%s/batch-service/p/%s/%s",
                hostName,
                projectName,
                target);
    }
}
