package aiss.GitLabMiner.service;

import aiss.GitLabMiner.model.Commit;
import aiss.GitLabMiner.model.Issue;
import aiss.GitLabMiner.transformer.IssueDef;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class IssueServiceTest {
    @Autowired
    IssueService issueService;
    @Test
    @DisplayName("Test de getAllIssues")
    void getAllIssues() {
        List<IssueDef> issueList = issueService.getAllIssues(179611, 1,2);
        System.out.println(issueList);
    }
}