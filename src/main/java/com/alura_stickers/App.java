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
            String title = movie.get("title");
            String poster = movie.get("image");
            // String rating = movie.get("imDbRating");
            String rating = "0.5";

            // Print movie info with decorations
            System.out.println("\u001b[1m" + title + "\u001b[0m");
            System.out.println(poster);
            System.out.println("Rating: " + Stars(rating) + " " + rating);
            System.out.println("\u001b[3m" + "-----------------------------" + "\u001b[0m");
        }
    }

    static String Stars(String note) {
        double rating;
        try {
            rating = Double.parseDouble(note);
        } catch (NumberFormatException e) {
            return "";
        }
        int stars = (int) Math.round(rating / 2.0);
        if (stars < 1) {
            stars = 1;
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < stars; i++) {
            sb.append("\u2B50"); 
        }
        return sb.toString();
    }
}
