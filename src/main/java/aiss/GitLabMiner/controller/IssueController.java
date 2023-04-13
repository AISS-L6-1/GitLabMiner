package aiss.GitLabMiner.controller;

import aiss.GitLabMiner.repository.IssueRepository;
import aiss.GitLabMiner.repository.ProjectRepository;
import aiss.GitLabMiner.transformer.IssueFinal;
import aiss.GitLabMiner.transformer.ProjectFinal;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/issues")
public class IssueController {
    private final IssueRepository repository;

    public IssueController(IssueRepository repository){this.repository = repository;}

    //GET http://localhost:8080/api/issues
    @GetMapping
    public List<IssueFinal> findAll(){
        return repository.findAll();
    }

    //GET http://localhost:8080/api/issues/{id}
    @GetMapping("/{id}")
    public IssueFinal findOne(@PathVariable String id){
        return repository.findOne(id);
    }

    //POST http://localhost:8080/api/issues/{id}
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public IssueFinal create(@Valid @RequestBody IssueFinal issue){
        return repository.create(issue);
    }

    //PUT http://localhost:8080/api/issues/{id}
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("/{id}")
    public void update(@Valid @RequestBody IssueFinal updatedIssue, @PathVariable String id){
        repository.update( updatedIssue, id);
    }

    //DELETE http://localhost:8080/api/issues/{id}
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id){
        repository.delete(id);
    }
}
