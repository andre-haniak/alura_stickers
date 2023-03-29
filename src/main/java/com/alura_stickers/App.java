package com.alura_stickers;

import java.io.InputStream;
import java.net.URL;
import java.util.List;
import java.util.Map;

public class App {
    public static void main(String[] args) throws Exception {

        // Making request to top contents API
        String url = "https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/Topcontents.json";

        ClientHttp http = new ClientHttp();
        String json = http.searchData(url);
        
        // Extracting data from the $body{title, image, rating}
        JsonParser parser = new JsonParser();
        List<Map<String, String>> contentList = parser.parse(json);

        // Listing all contents
        StickerGenerator generator = new StickerGenerator();

        for (Map<String,String> content : contentList) {

            String title = content.get("title");
            String rating = content.get("imDbRating");
            String urlImage = content.get("image");
            String fileName = title.replace(":", "-")  + ".png";

            InputStream inputStream = new URL(urlImage).openStream();
            generator.CreateSticker(inputStream, fileName, rating);


            // Print content info with decorations
            System.out.println("\u001b[1m" + title + "\u001b[0m");
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
