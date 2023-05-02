package utils;

import org.springframework.data.util.Pair;
import org.springframework.http.HttpHeaders;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

public class funciones {

    public static String getNextPageUrl(HttpHeaders headers) {
        String result = null;

        // If there is no link header, return null
        List<String> linkHeader = headers.get("Link");
        if (linkHeader == null)
            return null;

        // If the header contains no links, return null
        String links = linkHeader.get(0);
        if (links == null || links.isEmpty())
            return null;

        // Return the next page URL or null if none.
        for (String token : links.split(", ")) {
            if (token.endsWith("rel=\"next\"")) {
                // Found the next page. This should look something like
                // <https://api.github.com/repos?page=3&per_page=100>; rel="next"
                int idx = token.indexOf('>');
                result = token.substring(1, idx);
                break;
            }
        }

        return result;
    }

    public static String dateNDaysBefore(Integer n){
        LocalDateTime ahora = LocalDateTime.now();
        return ahora.minusDays(n).toString();
    }


    public static LocalDateTime StringToLocalDateTime(String dateToParse) {

        Instant instant = Instant.parse(dateToParse);
        LocalDateTime parsedDate = LocalDateTime.ofInstant(instant, ZoneOffset.UTC);

        return parsedDate;
    }
}
