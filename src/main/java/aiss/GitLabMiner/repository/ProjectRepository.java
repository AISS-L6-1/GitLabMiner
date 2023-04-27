package aiss.GitLabMiner.repository;

import aiss.GitLabMiner.model.Comment;
import aiss.GitLabMiner.model.Commit;
import aiss.GitLabMiner.model.Issue;
import aiss.GitLabMiner.model.Project;
import aiss.GitLabMiner.service.CommitService;
import aiss.GitLabMiner.service.IssueService;
import aiss.GitLabMiner.service.ProjectService;
import aiss.GitLabMiner.transformer.IssueFinal;
import aiss.GitLabMiner.transformer.ProjectFinal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Repository
public class ProjectRepository {

//    List<ProjectFinal> projectList = new ArrayList<>();
//
//    @Autowired
//    ProjectService projectService;
//    @Autowired
//    CommitService commitService;
//    @Autowired
//    IssueService issueService;
//
//    public ProjectRepository() {
//        // aquí es donde tengo que cargar el projectList de proyectos usando el getAllProjects y transformando
//
//        Commit commit1 = new Commit("1", "commit 1", "mensaje 1", "Pablo", "email",
//                null, "Pablo", "email", null, "url");
//        Commit commit2 = new Commit("1", "commit 2", "mensaje 2", "Alfonso", "email",
//                null, "Pablo", "email", null, "url");
//
//        IssueRepository issueRepository = new IssueRepository();
//        projectList.add(new ProjectFinal("1", "name", "url", List.of(commit1, commit2), issueRepository.issueList));
//        projectList.add(new ProjectFinal("2", "name", "url", List.of(commit1, commit2), issueRepository.issueList));
//    }
//
//
//    public List<ProjectFinal> findAll() {
//        return projectList;
//    }
//
//    public ProjectFinal findOne(String id) {
//        return projectList.stream().filter(project -> project.getId().equals(id)).findFirst().orElse(null);
//    }
//
//    public ProjectFinal create(ProjectFinal project){
//        ProjectFinal newProject = new ProjectFinal(
//                UUID.randomUUID().toString(),
//                project.getName(),
//                project.getWebUrl(),
//                project.getCommitList(),
//                project.getIssueList()
//        );
//        projectList.add(newProject);
//        return newProject;
//    }
//
//    public void update(ProjectFinal updatedProject, String id){
//        ProjectFinal existing = findOne(id);
//        int i = projectList.indexOf(existing);
//        updatedProject.setId(existing.getId());
//        projectList.set(i,updatedProject);
//    }
//
//    public void delete(String id){projectList.removeIf(commit -> commit.getId().equals(id));}
}
