package com.alura_stickers;

import java.io.InputStream;
import java.net.URL;
import java.util.List;

public class App {
    public static void main(String[] args) throws Exception {

        // Making request to top contents API
        // String url = "https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/Topcontents.json";
        String url = "https://api.nasa.gov/planetary/apod?api_key=DEMO_KEY&start_date=2023-02-10&end_date=2023-02-14";

        ClientHttp http = new ClientHttp();
        String json = http.searchData(url);
        

        //  Extracting and Listing all contents
        NasaContentExtractor extractor = new NasaContentExtractor();
        List<Content> contents = extractor.extractContents(json);

        StickerGenerator generator = new StickerGenerator();

        for (int i = 0; i < 2; i++) {

            Content content = contents.get(i);

            String title = content.getTitle();
            String urlImage = content.getUrlImage();

            // Print content info with decoration
            InputStream inputStream = new URL(urlImage).openStream();
            String fileName = title.replace(":", "-")  + ".png";

            generator.CreateSticker(inputStream, fileName);

            System.out.println("\u001b[1m" + title + "\u001b[0m");
            System.out.println("\u001b[3m" + "-----------------------------" + "\u001b[0m");


        }
    }

    // static String Stars(String note) {
    //     double rating;
    //     try {
    //         rating = Double.parseDouble(note);
    //     } catch (NumberFormatException e) {
    //         return "";
    //     }
    //     int stars = (int) Math.round(rating / 2.0);
    //     if (stars < 1) {
    //         stars = 1;
    //     }
    //     StringBuilder sb = new StringBuilder();
    //     for (int i = 0; i < stars; i++) {
    //         sb.append("\u2B50"); 
    //     }
    //     return sb.toString();
    // }
}
