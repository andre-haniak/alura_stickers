package com.alura_stickers;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class NasaContentExtractor implements ContentExtractor {

    public List<Content> extractContents(String json){

        // Extracting data from the $body{title, image}
        JsonParser parser = new JsonParser();
        List<Map<String, String>> attributeList = parser.parse(json);

        List<Content> contents = new ArrayList<>();

        //Popular content list
        for (Map<String, String> attributes: attributeList) {
            String title = attributes.get("title");
            String urlImage = attributes.get("url");
            Content content = new Content(title, urlImage);

            contents.add(content);
        }

        return contents;

    }

}
