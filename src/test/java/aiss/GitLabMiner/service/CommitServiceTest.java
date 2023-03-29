package aiss.GitLabMiner.service;

import aiss.GitLabMiner.model.Commit;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class CommitServiceTest {
    @Autowired
    CommitService commitService;
    @Test
    @DisplayName("Test de getAllCommits")
    void getAllCommits() {
        List<Commit> commitList = commitService.getAllCommits(44673732);
        System.out.println(commitList);
    }

    @Test
    @DisplayName("Test de getCommitFromId")
    void getCommitFromId() {
        Commit commit = commitService.getCommitFromId("ee6e291274fcca03801261f1fd0684aa32c6d140");
        System.out.println(commit);
    }

    @Test
    @DisplayName("Test de getCommitFromEmail")
    void getCommitFromEmail() {
        Commit commit = commitService.getCommitFromEmail("henry@pitest.org");
        System.out.println(commit);
    }
}