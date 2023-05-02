package aiss.GitLabMiner.service;

import aiss.GitLabMiner.model.Commit;
import aiss.GitLabMiner.model.Issue;
import aiss.GitLabMiner.model.Project;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpHeaders;
import utils.funciones;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@Service
public class CommitService {
    @Autowired
    RestTemplate restTemplate;

    public List<Commit> getAllCommits(Integer id, Integer sinceDays, Integer maxPages)
            throws HttpClientErrorException {// sinceDays() maxPages  y throws HttpClientErrorException

        String url = "https://gitlab.com/api/v4/projects/" + id.toString() + "/repository/commits";

        String parsedSince = funciones.dateNDaysBefore(sinceDays);

        //como queremos que nuestros parametros(sinceDays y maxPages) sean opcionales, debemos comprobar cual de ellos no es nulo
        // y en funcion de si existe uno o ambos añadir la ? en la posicion correspondiente
        if (sinceDays != null && maxPages != null) {
            LocalDateTime since = LocalDateTime.now().minusDays(sinceDays);
            url.concat("?created_after=" + parsedSince + "&" + "maxPages=" + maxPages);
        } else {
            if (sinceDays != null) {
                LocalDateTime since = LocalDateTime.now().minusDays(sinceDays);
                url.concat("?created_after=" + parsedSince);
            }
            else if (maxPages != null){
                url.concat("?maxPages=" + maxPages);
            }
        }

        String token = "glpat-yzJhzxFSm4fasdqqwCKD";
        HttpHeaders httpHeadersRequest = new HttpHeaders();
        httpHeadersRequest.setBearerAuth(token);
        HttpEntity<Commit[]> httpRequest = new HttpEntity<>(null, httpHeadersRequest);
        ResponseEntity<Commit[]> httpResponse = restTemplate.exchange(url, HttpMethod.GET, httpRequest, Commit[].class);
        HttpHeaders httpResponseHeaders = httpResponse.getHeaders();

        List<Commit> commitList= new ArrayList<>();
        commitList.addAll(Arrays.asList(httpResponse.getBody()));

        String siguientePagina = utils.funciones.getNextPageUrl(httpResponseHeaders);
        Integer page = 2;


        while (siguientePagina != null && (maxPages != null ?false:true && page < maxPages)) { //compruebo que maxPages sea distinto de null para poder avanzar
            ResponseEntity<Commit[]> responseEntity = restTemplate.exchange(url + "?page=" + String.valueOf(page), HttpMethod.GET, httpRequest, Commit[].class);
            commitList.addAll(Arrays.asList(responseEntity.getBody()));
            siguientePagina = utils.funciones.getNextPageUrl(responseEntity.getHeaders());
            page++;
        }

        return commitList;
    }
}
