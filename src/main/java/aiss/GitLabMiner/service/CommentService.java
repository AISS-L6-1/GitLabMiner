package aiss.GitLabMiner.service;

import aiss.GitLabMiner.model.Comment;
import aiss.GitLabMiner.model.Issue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import utils.Token;
import utils.funciones;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@Service
public class CommentService {
    @Autowired
    RestTemplate restTemplate;

    public List<Comment> getCommentsFromId(Integer id, Integer iId, Integer sinceDays, Integer maxPages)
    throws HttpClientErrorException{
        String url="https://gitlab.com/api/v4/projects/"+id.toString()+"/issues/"+iId.toString()+"/notes";

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
        HttpEntity<Comment[]> httpRequest = new HttpEntity<>(null, httpHeadersRequest);
        ResponseEntity<Comment[]> httpResponse = restTemplate.exchange(url, HttpMethod.GET, httpRequest, Comment[].class);
        HttpHeaders httpResponseHeaders = httpResponse.getHeaders();

        List<Comment> commentList = new ArrayList<>();
        commentList.addAll(Arrays.asList(httpResponse.getBody()));

        String siguientePagina = utils.funciones.getNextPageUrl(httpResponseHeaders);
        Integer page = 2;

        while (siguientePagina != null && (maxPages != null ?false:true && page < maxPages)) { //compruebo que maxPages sea distinto de null para poder avanzar
            ResponseEntity<Comment[]> responseEntity = restTemplate.exchange(url + "?page=" + String.valueOf(page), HttpMethod.GET, httpRequest, Comment[].class);
            commentList.addAll(Arrays.asList(responseEntity.getBody()));
            siguientePagina = utils.funciones.getNextPageUrl(responseEntity.getHeaders());
            page++;
        }
        return commentList;
    }
}
