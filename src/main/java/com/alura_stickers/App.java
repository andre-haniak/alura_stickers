package com.alura_stickers;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.List;
import java.util.Map;

public class App {
    public static void main(String[] args) throws Exception {

        // Making request to top movies API
        String url = "https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/TopMovies.json";

        URI address = URI.create(url);
        HttpClient httpClient = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder(address).GET().build();
        HttpResponse<String> response = httpClient.send(request, BodyHandlers.ofString());
        String body = response.body();
        
        // Extracting data from the $body{title, image, rating}
        JsonParser parser = new JsonParser();

        List<Map<String, String>> movieList = parser.parse(body);


        // Listing all movies
        for (Map<String,String> movie : movieList) {
            System.out.println(movie.get("title"));
            System.out.println(movie.get("image"));
            System.out.println(movie.get("imDbRating"));
        }
    }
}
