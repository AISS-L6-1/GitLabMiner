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
        List<Commit> commitList = commitService.getAllCommits(4207231, 50, 6);
        System.out.println(commitList);
    }

}