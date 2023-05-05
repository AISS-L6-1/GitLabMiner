package aiss.GitLabMiner.service;

import aiss.GitLabMiner.model.Project;
import aiss.GitLabMiner.transformer.ProjectDef;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
@SpringBootTest
class ProjectServiceTest {
    @Autowired
    ProjectService projectService;
    @Test
    @DisplayName("Test de getAllProjects")
    void getAllProjects() {
        List<ProjectDef> projectList = projectService.getAllProjects(2,10,10,5);
        assertFalse(projectList.isEmpty(),"The list of projects is empty");
        System.out.println(projectList);
    }

    @Test
    @DisplayName("Test de getProjectFromId")
    void getProjectFromId() {
        Integer id = 4207231; // está en las primeras 20 pags
        ProjectDef project = projectService.getProjectFromId(id, 10, 10, 5);
        assertNotNull(project.getId(), "The project id is null");
        assertNotNull(project.getName(), "The project name cannot be null");
        assertNotNull(project.getWeb_url(), "The url cannot be null");
        System.out.println(project);
    }
}