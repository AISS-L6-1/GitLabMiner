package aiss.GitLabMiner.service;

import aiss.GitLabMiner.model.Comment;
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
public class CommentService {
    @Autowired
    RestTemplate restTemplate;

    public List<Comment> getCommentsFromId(Integer id, Integer iId, Integer maxPages, Integer sinceDays)
    throws HttpClientErrorException{
        String url="https://gitlab.com/api/v4/projects/"+id.toString()+"/issues/"+iId.toString()+"/notes";
        List<Comment> commentList= new ArrayList<>();
        String token = "glpat-yzJhzxFSm4fasdqqwCKD";

        // compruebo que los parametros no sean nulos
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

        HttpHeaders httpHeadersRequest = new HttpHeaders();
        httpHeadersRequest.setBearerAuth(token);
        HttpEntity<Comment[]> httpRequest = new HttpEntity<>(null, httpHeadersRequest);
        ResponseEntity<Comment[]> httpResponse = restTemplate.exchange(url, HttpMethod.GET, httpRequest, Comment[].class);
        HttpHeaders httpResponseHeaders = httpResponse.getHeaders();

        String siguientePagina = utils.funciones.getNextPageUrl(httpResponseHeaders);
        Integer page = 1;

        while (siguientePagina != null && (maxPages != null && page < maxPages)) { //compruebo que maxPages sea distinto de null para poder avanzar
            ResponseEntity<Comment[]> responseEntity = restTemplate.exchange(url + "?page=" + String.valueOf(page), HttpMethod.GET, httpRequest, Comment[].class);
            commentList.addAll(Arrays.asList(responseEntity.getBody()));
            siguientePagina = utils.funciones.getNextPageUrl(responseEntity.getHeaders());
            page++;
        }
        System.out.println(commentList.size());
        return commentList;
    }
}
