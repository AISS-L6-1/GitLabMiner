package aiss.GitLabMiner.service;

import aiss.GitLabMiner.model.Project;
import aiss.GitLabMiner.transformer.ProjectDef;
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
        List<ProjectDef> projectList = projectService.getAllProjects(2,10,10,5);
        System.out.println(projectList);
    }

    @Test
    @DisplayName("Test de getProjectFromId")
    void getProjectFromId() {
        Integer id = 4207231; // está en las primeras 20 pags
        ProjectDef project = projectService.getProjectFromId(id, 10, 10, 5);
        System.out.println(project);
    }
}