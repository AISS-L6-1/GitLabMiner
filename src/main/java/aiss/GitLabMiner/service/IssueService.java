package aiss.GitLabMiner.service;


import aiss.GitLabMiner.model.Issue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@Service
public class IssueService {
    @Autowired
    RestTemplate restTemplate;

    public List<Issue> getAllIssues(Integer id, Integer sinceDays, Integer maxPages)
            throws HttpClientErrorException {

        String url = "https://gitlab.com/api/v4/projects/" + id.toString() + "/issues";
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
        HttpEntity<Issue[]> httpRequest = new HttpEntity<>(null, httpHeadersRequest);
        ResponseEntity<Issue[]> httpResponse = restTemplate.exchange(url, HttpMethod.GET, httpRequest, Issue[].class);
        HttpHeaders httpResponseHeaders = httpResponse.getHeaders();

        String siguientePagina = utils.funciones.getNextPageUrl(httpResponseHeaders);
        Integer page = 1;
        List<Issue> issueList = new ArrayList<>();
        while (siguientePagina != null && (maxPages != null && page < maxPages)) {//hay que comprobar que maxPages es diferente de null para poder evaluar <, funciona gracias a la evaluacion perezosa
            ResponseEntity<Issue[]> responseEntity = restTemplate.exchange(url + "?page=" + String.valueOf(page), HttpMethod.GET, httpRequest, Issue[].class);
            issueList.addAll(Arrays.asList(responseEntity.getBody()));
            siguientePagina = utils.funciones.getNextPageUrl(responseEntity.getHeaders());
            page++;
        }
        System.out.println(issueList.size());
        return issueList;
    }
}
