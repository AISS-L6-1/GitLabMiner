package aiss.GitLabMiner.controller;

import aiss.GitLabMiner.exceptions.AuthorizationException;
import aiss.GitLabMiner.exceptions.GitMinerNotRunningException;
import aiss.GitLabMiner.exceptions.ProjectNotFoundException;
import aiss.GitLabMiner.model.Project;
import aiss.GitLabMiner.service.ProjectService;
import aiss.GitLabMiner.transformer.ProjectDef;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClientException;

import java.util.List;

@RestController
@RequestMapping("/api/projects")
public class ProjectController {
    @Autowired
    ProjectService projectService;


    //GET http://localhost:8080/api/projects
    @GetMapping
    public List<Project> findAll(@RequestParam(value = "since", defaultValue = "5") Integer since, @RequestParam(value = "sinceIssues", defaultValue = "5") Integer sinceIssues, @RequestParam(value = "sinceCommits", defaultValue = "5") Integer sinceCommits, @RequestParam(value = "maxPages", defaultValue = "2") Integer maxPages)
            throws AuthorizationException {
        try {
            List<Project> projectList = projectService.getAllProjects(since, sinceIssues, sinceCommits, maxPages);
            return projectList;
        } catch (HttpClientErrorException.Unauthorized e) {
            throw new AuthorizationException("revisa que token esté bien creado que te lo he dicho 20 veces");

        }

    }


    //GET http://localhost:8081/api/projects/{id}
    @GetMapping("/{id}")
    public ProjectDef findOne(@PathVariable Integer id, @RequestParam(value = "sinceIssues", defaultValue = "5") Integer sinceIssues, @RequestParam(value = "sinceCommits", defaultValue = "5") Integer sinceCommits, @RequestParam(value = "maxPages", defaultValue = "2") Integer maxPages)
            throws ProjectNotFoundException, AuthorizationException {
        try {
            ProjectDef project = projectService.getProjectFromId(id, sinceIssues, sinceCommits, maxPages);
            return project;
        } catch (HttpClientErrorException.NotFound e) {
            throw new ProjectNotFoundException("Che no lo encontré");

        } catch (HttpClientErrorException.Unauthorized e) {
            throw new AuthorizationException("revisa que token esté bien creado que te lo he dicho 20 veces");

        }
    }


    //POST http://localhost:8081/api/projects/{id}
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/{id}")
    public ProjectDef create(@PathVariable Integer id, @RequestParam(value = "sinceIssues", defaultValue = "5") Integer sinceIssues, @RequestParam(value = "sinceCommits", defaultValue = "5") Integer sinceCommits, @RequestParam(value = "maxPages", defaultValue = "2") Integer maxPages)
            throws GitMinerNotRunningException, ProjectNotFoundException, AuthorizationException {
        try {
            ProjectDef projectDef = projectService.postProjectFromId(id, sinceIssues, sinceCommits, maxPages);
            return projectDef;
        } catch (HttpClientErrorException.NotFound e) {
            throw new ProjectNotFoundException("Che no lo encontré");

        } catch (HttpClientErrorException.Unauthorized e) {
            throw new AuthorizationException("revisa que token esté bien creado que te lo he dicho 20 veces");

        } catch (RestClientException e) {
            throw new GitMinerNotRunningException("arrancá el GitMiner pelotudo");
        }
    }
}
