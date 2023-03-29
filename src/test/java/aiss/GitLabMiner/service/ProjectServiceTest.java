package aiss.GitLabMiner.service;

import aiss.GitLabMiner.model.Project;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
@SpringBootTest
class ProjectServiceTest {
    @Autowired
    ProjectService projectService;
    @Test
    @DisplayName("Test de getAllProjects")
    void getAllProjects() {
        List<Project> projectList = projectService.getAllProjects();
        System.out.println(projectList);
    }

    @Test
    @DisplayName("Test de getProjectFromId")
    void getProjectFromId() {
        Integer id = 44673732;
        Project project = projectService.getProjectFromId(id);
        System.out.println(project);
    }
}