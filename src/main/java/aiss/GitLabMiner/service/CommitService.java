package aiss.GitLabMiner.service;

import aiss.GitLabMiner.model.Commit;
import aiss.GitLabMiner.model.Project;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpHeaders;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static utils.funciones.ultimaPagina;

@Service
public class CommitService {
    @Autowired
    RestTemplate restTemplate;

    public List<Commit> getAllCommits(Integer id, Integer sinceDays, Integer maxPages)
            throws HttpClientErrorException {//sinceDays() maxPages  y throws HttpClientErrorException


        String url = "https://gitlab.com/api/v4/projects/" + id.toString() + "/repository/commits";
        //como queremos que nuestros parametros(sinceDays y maxPages) sean opcionales, debemos comprobar cual de ellos no es nulo
        // y en funcion de si existe uno o ambos añadir la ? en la posicion correspondiente
        if (sinceDays != null && maxPages != null) {
            LocalDateTime since = LocalDateTime.now().minusDays(sinceDays);
            url.concat("?since=" + since + "&" + "maxPages=" + maxPages);
        } else {
            if (sinceDays != null) {
                LocalDateTime since = LocalDateTime.now().minusDays(sinceDays);
                url.concat("?since=" + since);
            }
            else {
                url.concat("?maxPages=" + maxPages);
            }
        }
        String token = "glpat-yzJhzxFSm4fasdqqwCKD";
        HttpHeaders httpHeadersRequest = new HttpHeaders();
        httpHeadersRequest.setBearerAuth(token);
        HttpEntity<Commit[]> httpRequest = new HttpEntity<>(null, httpHeadersRequest);
        ResponseEntity<Commit[]> httpResponse = restTemplate.exchange(url, HttpMethod.GET, httpRequest, Commit[].class);
        HttpHeaders httpResponseHeaders = httpResponse.getHeaders();
        List<String> linkHeader = httpResponseHeaders.get("Link");

        Integer paginaUltima = ultimaPagina(linkHeader);
        if(maxPages != null && maxPages < paginaUltima){
            paginaUltima = maxPages;
        }
        List<Commit> commitList = new ArrayList<>();
        for (int i = 1; i <= paginaUltima; i++) {
            Commit[] commitArray = restTemplate.exchange(url + "?page=" + String.valueOf(i), HttpMethod.GET, httpRequest, Commit[].class).getBody();
            commitList.addAll(Arrays.asList(commitArray));
        }
        return commitList;
    }

    @Autowired
    ProjectService projectService;

    public Commit getCommitFromId(String id) {
        List<Project> projectList = projectService.getAllProjects();
        List<Commit> commitList = projectList.stream().flatMap(project -> getAllCommits(project.getId(),null,null).stream()).toList();
        return commitList.stream().filter(commit -> commit.getId().equals(id)).findFirst().orElse(null);
    }

    public Commit getCommitFromEmail(String email) {
        List<Project> projectList = projectService.getAllProjects();
        List<Commit> commitList = projectList.stream().flatMap(project -> getAllCommits(project.getId(),null,null).stream()).toList();
        return commitList.stream().filter(commit -> commit.getCommitterEmail().equals(email)).findFirst().orElse(null);
    }
}
