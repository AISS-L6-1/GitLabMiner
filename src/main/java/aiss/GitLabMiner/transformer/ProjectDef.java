package aiss.GitLabMiner.transformer;

import aiss.GitLabMiner.model.Commit;
import aiss.GitLabMiner.model.Issue;
import aiss.GitLabMiner.model.Project;
import aiss.GitLabMiner.service.CommitService;
import aiss.GitLabMiner.service.IssueService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class ProjectDef {

    private String id;
    private String name;
    private String web_url;
    private List<Commit> listCommits;
    private List<IssueDef> listIssue;

    //este metodo transforma un proyecto (modelo POJO) en el proyecto (modelo figura 2) al invocarlo se buscan todos los issues(que se transforman en IssueDef, ver getAllIssues) y todos los Comments del proyecto
    public static ProjectDef ofRaw(Project project, CommitService commitService, IssueService issueService, Integer sinceIssues, Integer sinceCommits, Integer maxPages){
        return new ProjectDef(project.getId(), project.getName(), project.getWebUrl(),
                commitService.getAllCommits(Integer.valueOf(project.getId()), sinceCommits, maxPages),
                issueService.getAllIssues(Integer.valueOf(project.getId()), sinceIssues, maxPages));
    }

    public ProjectDef(String id, String name, String web_url, List<Commit> listCommits, List<IssueDef> listIssue) {
        this.id = id;
        this.name = name;
        this.web_url = web_url;
        this.listCommits = listCommits;
        this.listIssue = listIssue;
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

    public List<Commit> getListCommits() {
        return listCommits;
    }

    public void setListCommits(List<Commit> listCommits) {
        this.listCommits = listCommits;
    }

    public List<IssueDef> getListIssue() {
        return listIssue;
    }

    public void setListIssue(List<IssueDef> listIssue) {
        this.listIssue = listIssue;
    }

    @Override
    public String toString() {
        return "ProjectDef{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", web_url='" + web_url + '\'' +
                ", listCommits=" + listCommits +
                ", listIssue=" + listIssue +
                '}';
    }
}
