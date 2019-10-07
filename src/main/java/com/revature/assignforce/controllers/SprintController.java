package com.revature.assignforce.controllers;

import com.revature.assignforce.beans.SprintDTO;
import com.revature.assignforce.service.IssuesServiceProvider;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

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
@RequestMapping("/p/{name}")
@Api(value = "SprintController")
public class SprintController {

    @Value("${spring.cloud.inetutils.default-hostname}")
    String hostName;

    private IssuesServiceProvider issuesServiceProvider;

    public SprintController() {/*default*/}

    @Autowired
    public void setIssuesServiceProvider(IssuesServiceProvider issuesServiceProvider) {
        this.issuesServiceProvider = issuesServiceProvider;
    }

	/**
	 * Find All Sprints using a get request and return a list of items
	 * 
	 * @return	ResponseEntity
	 */
    @ApiOperation(value = "List all Sprints from the System", response = ResponseEntity.class, tags = "SprintController")
    @GetMapping("/sprints")
    public ResponseEntity<List<SprintDTO>> getProjectSprints(@ApiParam(name="name") @PathVariable String name) {
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

	/**
	 * Find Sprint by id using get request and return status 200 - OK. 
	 * 
	 * @param id	Sprint by Id
	 * @return		ResponseEntity
	 */
    @ApiOperation(value = "Get Sprint by Id from the System", response = ResponseEntity.class, tags = "SprintController")
    @GetMapping("sprint/{id}")
    public ResponseEntity<SprintDTO> getSprintById(@ApiParam(name="id") @PathVariable int id) {
        return new ResponseEntity<>(null, HttpStatus.OK);
    }

	/**
	 * Find Sprint columns by Id
	 * 
	 * @return	ResponseEntity 
	 */
    @ApiOperation(value = "Get all Sprint Columns", response = ResponseEntity.class, tags = "SprintController")
    @GetMapping("sprint/{id}/columns")
    public ResponseEntity getSprintColumns(@ApiParam(name="id") @PathVariable int id) {
        return null;
    }

    private String transformUrl(String projectName, String target) {
        return String.format("%s/batch-service/p/%s/%s",
                hostName,
                projectName,
                target);
    }
}
