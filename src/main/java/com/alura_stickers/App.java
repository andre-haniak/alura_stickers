package com.alura_stickers;

import java.io.InputStream;
import java.net.URL;
import java.util.List;

public class App {
    public static void main(String[] args) throws Exception {

        // Making request to top contents API
        API api = API.TOP_LANGUAGES;
        String url = api.getUrl();
        ClientHttp http = new ClientHttp();
        String json = http.searchData(url);
        

        //  Extracting and Listing all contents
        ContentExtractor extractor = api.getExtractor();
        List<Content> contents = extractor.extractContents(json);

        StickerGenerator generator = new StickerGenerator();

        for (int i = 0; i < contents.size(); i++) {

            Content content = contents.get(i);
            String title = content.title();
            String urlImage = content.urlImage();

            // Print content info with decoration
            InputStream inputStream = new URL(urlImage).openStream();
            String fileName = title.replace(":", "-")  + ".png";

            generator.CreateSticker(inputStream, fileName);

            System.out.println("\u001b[1m" + title + "\u001b[0m");
            System.out.println("\u001b[3m" + "-----------------------------" + "\u001b[0m");
        }
    }
}
