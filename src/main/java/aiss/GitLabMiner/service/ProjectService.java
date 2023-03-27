package aiss.GitLabMiner.service;

import aiss.GitLabMiner.model.Project;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Service
public class ProjectService {
    @Autowired
    RestTemplate restTemplate;
    public List<Project> getAllProjects() {
        String url = "https://gitlab.com/api/v4/projects";
        return Arrays.asList(restTemplate.getForObject(url, Project[].class));
    }
}
