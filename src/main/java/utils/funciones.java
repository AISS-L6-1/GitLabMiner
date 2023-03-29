package utils;

import org.springframework.data.util.Pair;

import java.util.Arrays;
import java.util.List;

public class funciones {

    public static Integer ultimaPagina(List<String> linkHeader){ //dado un link (campo del header de http response) devuelve un pair cuyos elementos son la pagina act
        Integer inicio;
        Integer fin;
        String ultimaUrl;
        Integer paginaUltima;

        if(linkHeader.get(0).split(", ").length == 3) {  //hay mas de 2 paginas
            ultimaUrl = linkHeader.get(0).split(", ")[2];   //accedemos al string que tiene ref="last"
            inicio = ultimaUrl.indexOf("page=") + "page=".length();
            fin = ultimaUrl.indexOf("&per_page=");
            paginaUltima = Integer.valueOf(ultimaUrl.substring(inicio, fin));   //obtenemos el valor de page=X de la última página

        }
        else {
            if(Arrays.stream(linkHeader.get(0).split(", ")).toList().get(0).contains("next")){ //hay dos paginas
                paginaUltima = 2;  //como estamos en un caso en el que solo hay dos paginas, la siguiente(y ultima) es la 2

            }
            else{ //hay una pagina
                paginaUltima = 1;
            }
        }
        return paginaUltima;
    }
}
