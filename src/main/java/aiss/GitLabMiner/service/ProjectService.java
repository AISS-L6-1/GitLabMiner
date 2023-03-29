package aiss.GitLabMiner.service;

import aiss.GitLabMiner.model.Commit;
import aiss.GitLabMiner.model.Project;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static utils.funciones.ultimaPagina;

@Service
public class ProjectService {
    @Autowired
    RestTemplate restTemplate;
    public List<Project> getAllProjects() {
        String url = "https://gitlab.com/api/v4/projects";
        String token = "glpat-yzJhzxFSm4fasdqqwCKD";
        HttpHeaders httpHeadersRequest = new HttpHeaders();
        httpHeadersRequest.setBearerAuth(token);
        HttpEntity<Project[]> httpRequest = new HttpEntity<>(null, httpHeadersRequest);
        ResponseEntity<Project[]> httpResponse = restTemplate.exchange(url, HttpMethod.GET, httpRequest, Project[].class);
        HttpHeaders httpResponseHeaders = httpResponse.getHeaders();
        List<String> linkHeader = httpResponseHeaders.get("Link");
        Integer paginaUltima = ultimaPagina(linkHeader);
        List<Project> projectList = new ArrayList<>();
        for (int i = 1; i <= paginaUltima; i++) {
            Project[] projectArray = restTemplate.exchange(url + "?page=" + String.valueOf(i), HttpMethod.GET, httpRequest, Project[].class).getBody();
            projectList.addAll(Arrays.asList(projectArray));
        }
        return projectList;
    }

    public Project getProjectFromId(Integer id) {
        String url = "https://gitlab.com/api/v4/projects" + "/" + id.toString();
        String token = "glpat-yzJhzxFSm4fasdqqwCKD";
        HttpHeaders httpHeadersRequest = new HttpHeaders();
        httpHeadersRequest.setBearerAuth(token);
        HttpEntity<Project> httpRequest = new HttpEntity<>(null, httpHeadersRequest);
        ResponseEntity<Project> httpResponse = restTemplate.exchange(url, HttpMethod.GET, httpRequest, Project.class);
        return httpResponse.getBody();
    }
}
