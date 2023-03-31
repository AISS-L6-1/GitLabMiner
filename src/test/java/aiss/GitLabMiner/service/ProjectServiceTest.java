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
        List<Project> projectList = projectService.getAllProjects(20,null);
        System.out.println(projectList);
    }

//    @Test
//    @DisplayName("Test de getProjectFromId")
//    void getProjectFromId() {
//        Integer id = 44773587; // está en las primeras 20 pags
//        Project project = projectService.getProjectFromId(id);
//        System.out.println(project);
//    }
}