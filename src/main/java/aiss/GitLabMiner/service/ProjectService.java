package aiss.GitLabMiner.service;

import aiss.GitLabMiner.model.Comment;
import aiss.GitLabMiner.model.Commit;
import aiss.GitLabMiner.model.Issue;
import aiss.GitLabMiner.model.Project;
import aiss.GitLabMiner.transformer.IssueDef;
import aiss.GitLabMiner.transformer.ProjectDef;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import utils.Token;
import utils.funciones;

import java.net.http.HttpResponse;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@Service
public class ProjectService {
    @Autowired
    RestTemplate restTemplate;
    @Autowired
    CommitService commitService;
    @Autowired
    IssueService issueService;
    @Autowired
    CommentService commentService;

    public List<Project> getAllProjects(Integer sinceDays, Integer sinceIssues, Integer sinceCommits, Integer maxPages)
            throws HttpClientErrorException {
        String url = "https://gitlab.com/api/v4/projects";

        //como queremos que nuestros parametros(sinceDays y maxPages) sean opcionales, debemos comprobar cual de ellos no es nulo
        // y en funcion de si existe uno o ambos añadir la ? en la posicion correspondiente
        if (sinceDays != null && maxPages != null) {
            LocalDateTime since = LocalDateTime.now().minusDays(sinceDays);
            url = url.concat("?created_after=" + since + "&" + "maxPages=" + maxPages);
        } else {
            if (sinceDays != null) {
                LocalDateTime since = LocalDateTime.now().minusDays(sinceDays);
                url = url.concat("?created_after=" + since);
            }
            else if (maxPages != null){
                url = url.concat("?maxPages=" + maxPages);
            }
        }

        String token = Token.TOKEN;

        HttpHeaders httpHeadersRequest = new HttpHeaders();
        httpHeadersRequest.setBearerAuth(token);
        HttpEntity<Project[]> httpRequest = new HttpEntity<>(null, httpHeadersRequest);
        ResponseEntity<Project[]> httpResponse = restTemplate.exchange(url, HttpMethod.GET, httpRequest, Project[].class);
        HttpHeaders httpResponseHeaders = httpResponse.getHeaders();

        List<Project> projectList = new ArrayList<>();
        projectList.addAll(Arrays.asList(httpResponse.getBody()));

        String siguientePagina = utils.funciones.getNextPageUrl(httpResponseHeaders);
        Integer page = 2;

        while (siguientePagina != null && (maxPages == null ? true:false || page < maxPages)) { //compruebo que maxPages sea distinto de null para poder avanzar
            ResponseEntity<Project[]> responseEntity = restTemplate.exchange(url + "?page=" + String.valueOf(page), HttpMethod.GET, httpRequest, Project[].class);
            projectList.addAll(Arrays.asList(responseEntity.getBody()));
            siguientePagina = utils.funciones.getNextPageUrl(responseEntity.getHeaders());
            page++;
        }
        return projectList;
    }

    public ProjectDef getProjectFromId(Integer id, Integer sinceIssues, Integer sinceCommits ,Integer maxPages)
            throws HttpClientErrorException{

        String url = "https://gitlab.com/api/v4/projects" + "/" + id.toString();
        String token = Token.TOKEN;

        HttpHeaders httpHeadersRequest = new HttpHeaders();
        httpHeadersRequest.setBearerAuth(token);
        HttpEntity<Project> httpRequest = new HttpEntity<>(null, httpHeadersRequest);
        ResponseEntity<Project> httpResponse = restTemplate.exchange(url, HttpMethod.GET, httpRequest, Project.class);

        //EXTRACCION
        //obtenemos el proyecto
        Project p = httpResponse.getBody();
        //obtenemos sus commits
        List<Commit> listCommits = commitService.getAllCommits(Integer.valueOf(p.getId()), sinceCommits, maxPages);
        //obtenemos sus issues
        List<Issue> ListIssues = issueService.getAllIssues(Integer.valueOf(p.getId()), sinceIssues, maxPages);

        List<IssueDef> listIssueDef = new ArrayList<>();

        //de cada issue obtenemos sus comments
        if(!ListIssues.isEmpty()){
            for (Issue i : ListIssues){
                List<Comment> commentList = commentService.getCommentsFromId(Integer.valueOf(p.getId()),Integer.valueOf(i.getRef_id()),sinceIssues,maxPages);

        //TRANSFORMACION
            //transformamos los issues en IssueDef
                IssueDef iDef = IssueDef.transformaIssue(i,commentList);
                listIssueDef.add(iDef);
            }
        }
            //transforma el proyecto en ProjectDef
        ProjectDef pDef = ProjectDef.transformaProject(p, listCommits,listIssueDef);
        return pDef;
    }

    public ProjectDef postProjectFromId(Integer id, Integer sinceIssues, Integer sinceCommits ,Integer maxPages)
            throws RestClientException{

        String url = "http://localhost:8080/gitminer/projects";

        HttpHeaders httpHeadersRequest = new HttpHeaders();
        HttpEntity<ProjectDef> httpRequest = new HttpEntity<>(null, httpHeadersRequest);

        ProjectDef proyecto = getProjectFromId(id,sinceIssues,sinceCommits,maxPages);

        ResponseEntity<ProjectDef> httpResponse = restTemplate.postForEntity(url, proyecto, ProjectDef.class);
        return httpResponse.getBody();
    }
}
