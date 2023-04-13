package aiss.GitLabMiner.service;

import aiss.GitLabMiner.model.Project;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.net.http.HttpResponse;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@Service
public class ProjectService {
    @Autowired
    RestTemplate restTemplate;

    public List<Project> getAllProjects(Integer sinceDays,Integer maxPages)
            throws HttpClientErrorException {
        String url = "https://gitlab.com/api/v4/projects";

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
            else if (maxPages != null){
                url.concat("?maxPages=" + maxPages);
            }
        }

        String token = "glpat-yzJhzxFSm4fasdqqwCKD";
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
