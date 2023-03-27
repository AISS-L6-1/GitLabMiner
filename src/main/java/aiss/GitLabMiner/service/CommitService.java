package aiss.GitLabMiner.service;

import aiss.GitLabMiner.model.Commit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpHeaders;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class CommitService {
    @Autowired
    RestTemplate restTemplate;
    public List<Commit> getAllCommits(Integer id) {
        String url = "https://gitlab.com/api/v4/projects/" + id.toString() + "/repository/commits";
        String token = "glpat-yzJhzxFSm4fasdqqwCKD";
        HttpHeaders httpHeadersRequest = new HttpHeaders();
        httpHeadersRequest.setBearerAuth(token);
        HttpEntity<Commit[]> httpRequest = new HttpEntity<>(null, httpHeadersRequest);
        ResponseEntity<Commit[]> httpResponse = restTemplate.exchange(url, HttpMethod.GET, httpRequest, Commit[].class);
        HttpHeaders httpResponseHeaders = httpResponse.getHeaders();
        List<String> linkHeader = httpResponseHeaders.get("Link");
        String primeraUrl = linkHeader.get(0).split(", ")[1];
        Integer inicio = primeraUrl.indexOf("page=") + "page=".length();
        Integer fin = primeraUrl.indexOf("&per_page=");
        Integer paginaActual = Integer.valueOf(primeraUrl.substring(inicio, fin));
        String ultimaUrl = linkHeader.get(0).split(", ")[2];
        inicio = ultimaUrl.indexOf("page=") + "page=".length();
        fin = ultimaUrl.indexOf("&per_page=");
        Integer paginaUltima = Integer.valueOf(ultimaUrl.substring(inicio, fin));
        List<Commit> commitList = new ArrayList<>();
        for (int i = paginaActual; i <= paginaUltima; i++) {
            Commit[] commitArray = restTemplate.exchange(url + "?page=" + paginaActual.toString(), HttpMethod.GET, httpRequest, Commit[].class).getBody();
            commitList.addAll(Arrays.asList(commitArray));
        }
        return commitList;
    }
}
