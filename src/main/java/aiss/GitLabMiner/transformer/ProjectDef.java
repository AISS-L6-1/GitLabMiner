package aiss.GitLabMiner.transformer;

import aiss.GitLabMiner.model.Commit;
import aiss.GitLabMiner.model.Project;
import aiss.GitLabMiner.service.CommitService;
import aiss.GitLabMiner.service.IssueService;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.List;


public class ProjectDef {

    private String id;

    private String name;

    private String web_url;

    private List<Commit> commits;

    private List<IssueDef> issues;

    //este metodo transforma un proyecto (modelo POJO) en el proyecto (modelo figura 2) al invocarlo se buscan todos los issues(que se transforman en IssueDef, ver getAllIssues) y todos los Comments del proyecto
    public static ProjectDef transformaProject(Project project, List<Commit> commitList,List<IssueDef> issueDefList){
        return new ProjectDef(project.getId(), project.getName(), project.getWebUrl(),commitList,issueDefList);
    }

    public ProjectDef(){

    }
    public ProjectDef(String id, String name, String web_url, List<Commit> listCommits, List<IssueDef> listIssue) {
        this.id = id;
        this.name = name;
        this.web_url = web_url;
        this.commits = listCommits;
        this.issues = listIssue;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getWeb_url() {
        return web_url;
    }

    public void setWeb_url(String web_url) {
        this.web_url = web_url;
    }

    public List<Commit> getCommits() {
        return commits;
    }

    public void setCommits(List<Commit> commits) {
        this.commits = commits;
    }

    public List<IssueDef> getIssues() {
        return issues;
    }

    public void setIssues(List<IssueDef> issues) {
        this.issues = issues;
    }

    @Override
    public String toString() {
        return "ProjectDef{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", web_url='" + web_url + '\'' +
                ", listCommits=" + commits +
                ", listIssue=" + issues +
                '}';
    }
}
