package aiss.GitLabMiner.controller;

import aiss.GitLabMiner.repository.ProjectRepository;
import aiss.GitLabMiner.transformer.ProjectFinal;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/projects")
public class ProjectController {
    private final ProjectRepository repository;

    public ProjectController(ProjectRepository repository){this.repository = repository;}

    //GET http://localhost:8080/api/projects
    @GetMapping
    public List<ProjectFinal> findAll(){
        return repository.findAll();
    }

    //GET http://localhost:8080/api/projects/{id}
    @GetMapping("/{id}")
    public ProjectFinal findOne(@PathVariable String id){
        return repository.findOne(id);
    }

    //POST http://localhost:8080/api/projects/{id}
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public ProjectFinal create(@Valid @RequestBody ProjectFinal project){
        return repository.create(project);
    }

    //PUT http://localhost:8080/api/projects/{id}
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("/{id}")
    public void update(@Valid @RequestBody ProjectFinal updatedAlbum, @PathVariable String id){
        repository.update(updatedAlbum, id);
    }

    //DELETE http://localhost:8080/api/projects/{id}
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id){
        repository.delete(id);
    }

}
