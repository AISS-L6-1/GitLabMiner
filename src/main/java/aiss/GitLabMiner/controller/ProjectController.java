package aiss.GitLabMiner.controller;

import aiss.GitLabMiner.service.ProjectService;
import aiss.GitLabMiner.transformer.ProjectDef;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/projects")
public class ProjectController {
    @Autowired
    ProjectService projectService;

    //GET http://localhost:8080/api/projects
    @GetMapping
    public List<ProjectDef> findAll( @RequestParam("since") Integer since, @RequestParam("sinceIssues") Integer sinceIssues, @RequestParam("sinceCommits") Integer sinceCommits, @RequestParam("maxPages") Integer maxPages){
        // a partir de un projectId, dame todos los commits y todos los issues (los commits e issues se le añaden en ofRaw)
        List<ProjectDef> projectList = projectService.getAllProjects(since, sinceIssues, sinceCommits, maxPages);
        return projectList;

    }

    //GET http://localhost:8081/api/projects/{id}
    @GetMapping("/{id}")
    public ProjectDef findOne(@PathVariable long id, @RequestParam("sinceIssues") Integer sinceIssues, @RequestParam("sinceCommits") Integer sinceCommits, @RequestParam("maxPages") Integer maxPages) {
        ProjectDef project = projectService.getProjectFromId((int) id, sinceIssues, sinceCommits, maxPages);
        return project;
    }

    //POST http://localhost:8081/api/projects/{id} // <-- este post lo tengo que redirigir a GHM o algo así?

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/{id}")
    public void create(@PathVariable Integer id, @RequestParam("sinceIssues") Integer sinceIssues, @RequestParam("sinceCommits") Integer sinceCommits, @RequestParam("maxPages") Integer maxPages){
        projectService.postProjectFromId(id,sinceIssues,sinceCommits,maxPages);

    }
}
