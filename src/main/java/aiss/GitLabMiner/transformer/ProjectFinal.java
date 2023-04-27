package aiss.GitLabMiner.transformer;


import aiss.GitLabMiner.model.Commit;
import aiss.GitLabMiner.model.Issue;
import aiss.GitLabMiner.model.Project;

import java.util.List;

public class ProjectFinal {
    private long id;

    private String name;

    private String webUrl;

    private List<Commit> commitList;

    private List<IssueFinal> issueList;

    public ProjectFinal(Project project, List<Commit> commitList, List<IssueFinal> issueList) {
        this.id = project.getId();
        this.name = project.getName();
        this.webUrl = project.getWebUrl();
        this.commitList = commitList;
        this.issueList = issueList;
    }
    public ProjectFinal(long id, String name, String webUrl, List<Commit> commitList, List<IssueFinal> issueList) {
        this.id = id;
        this.name = name;
        this.webUrl = webUrl;
        this.commitList = commitList;
        this.issueList = issueList;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getWebUrl() {
        return webUrl;
    }

    public void setWebUrl(String webUrl) {
        this.webUrl = webUrl;
    }

    public List<Commit> getCommitList() {
        return commitList;
    }

    public void setCommitList(List<Commit> commitList) {
        this.commitList = commitList;
    }

    public List<IssueFinal> getIssueList() {
        return issueList;
    }

    public void setIssueList(List<IssueFinal> issueList) {
        this.issueList = issueList;
    }
}
