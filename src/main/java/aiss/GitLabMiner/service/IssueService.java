package aiss.GitLabMiner.service;


import aiss.GitLabMiner.model.Issue;
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
public class IssueService {
    @Autowired
    RestTemplate restTemplate;

    public List<Issue> getAllIssues(Integer id){
        String url = "https://gitlab.com/api/v4/projects/" + id.toString() + "/issues";
        String token = "glpat-yzJhzxFSm4fasdqqwCKD";
        HttpHeaders httpHeadersRequest = new HttpHeaders();
        httpHeadersRequest.setBearerAuth(token);
        HttpEntity<Issue[]> httpRequest = new HttpEntity<>(null, httpHeadersRequest);
        ResponseEntity<Issue[]> httpResponse = restTemplate.exchange(url, HttpMethod.GET, httpRequest, Issue[].class);
        HttpHeaders httpResponseHeaders = httpResponse.getHeaders();

        List<String> linkHeader = httpResponseHeaders.get("Link");
        List<Issue> issueList = new ArrayList<>();

        // Integer paginaActual = 1;   //siempre obtenemos la primera pagina al hacer el get paginaActual inicializada con valor 1
        Integer paginaUltima = ultimaPagina(linkHeader);

        for (int i = 1; i <= paginaUltima; i++) {
            Issue[] issueArray = restTemplate.exchange(url + "?page=" + String.valueOf(i), HttpMethod.GET, httpRequest, Issue[].class).getBody();
            issueList.addAll(Arrays.asList(issueArray));
        }
        System.out.println(paginaUltima);
        return issueList;
    }
}
