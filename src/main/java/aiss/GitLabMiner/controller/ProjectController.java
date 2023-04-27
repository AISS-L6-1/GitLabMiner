package aiss.GitLabMiner.controller;

import aiss.GitLabMiner.model.Comment;
import aiss.GitLabMiner.model.Commit;
import aiss.GitLabMiner.model.Issue;
import aiss.GitLabMiner.model.Project;
import aiss.GitLabMiner.repository.ProjectRepository;
import aiss.GitLabMiner.service.CommentService;
import aiss.GitLabMiner.service.CommitService;
import aiss.GitLabMiner.service.IssueService;
import aiss.GitLabMiner.service.ProjectService;
import aiss.GitLabMiner.transformer.IssueFinal;
import aiss.GitLabMiner.transformer.ProjectFinal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/projects")
public class ProjectController {
    @Autowired
    ProjectService projectService;
    @Autowired
    CommitService commitService;
    @Autowired
    IssueService issueService;
    @Autowired
    CommentService commentService;

    //GET http://localhost:8080/api/projects
    @GetMapping
    public List<ProjectFinal> findAll(@RequestParam("since") Integer since, @RequestParam("maxDays") Integer maxDays){
        // a partir de un projectId, dame todos los commits y todos los issues (y por cada issue dame todos los comentarios)
        List<Project> projectList = projectService.getAllProjects(since, maxDays);
        List<ProjectFinal> projectFinalList = new ArrayList<>();
        for (Project project : projectList) {
            // hacer transformación:
            List<Commit> commitList = commitService.getAllCommits(project.getId(), since, maxDays);
            List<Issue> issueList = issueService.getAllIssues(project.getId(), since, maxDays);
            List<IssueFinal> issueFinalList = new ArrayList<>();
            for (Issue issue : issueList) {
                // para transformarlo a IssueFinal me hacen falta los comments solo
                List<Comment> commentList = commentService.getCommentsFromId(project.getId(), issue.getIid(), since, maxDays);
                IssueFinal issueFinal = new IssueFinal(issue.getId().toString(), issue.getIid().toString(), issue.getTitle(), issue.getDescription(), issue.getState(), issue.getCreatedAt(),
                issue.getUpdatedAt(), issue.getClosedAt(), issue.getLabels(), issue.getUpvotes(), issue.getDownvotes(), issue.getAuthor(), issue.getAsignee(), commentList);
                issueFinalList.add(issueFinal);
            }
            ProjectFinal projectFinal = new ProjectFinal(project, commitList, issueFinalList);
            projectFinalList.add(projectFinal);
        }
        return projectFinalList;

    }

    //GET http://localhost:8080/api/projects/{id}
    @GetMapping("/{id}")
    public ProjectFinal findOne(@PathVariable long id) {
        Project project = projectService.getProjectFromId((int) id);
        List<Commit> commitList = commitService.getAllCommits(project.getId(), null, 100);
        List<Issue> issueList = issueService.getAllIssues(project.getId(), null, 100);
        List<IssueFinal> issueFinalList = new ArrayList<>();
        for (Issue issue : issueList) {
            // para transformarlo a IssueFinal me hacen falta los comments solo
            List<Comment> commentList = commentService.getCommentsFromId(project.getId(), issue.getIid(), null, 100);
            IssueFinal issueFinal = new IssueFinal(issue.getId().toString(), issue.getIid().toString(), issue.getTitle(), issue.getDescription(), issue.getState(), issue.getCreatedAt(),
                    issue.getUpdatedAt(), issue.getClosedAt(), issue.getLabels(), issue.getUpvotes(), issue.getDownvotes(), issue.getAuthor(), issue.getAsignee(), commentList);
            issueFinalList.add(issueFinal);
        }
        return new ProjectFinal(project, commitList, issueFinalList);
    }
    //POST http://localhost:808X/api/projects/{id} // <-- este post lo tengo que redirigir a GHM o algo así?

}
