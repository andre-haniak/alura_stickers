package com.alura_stickers;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class NasaContentExtractor implements ContentExtractor {

    public List<Content> extractContents(String json){

        // Extracting data from the $body{title, image}
        JsonParser parser = new JsonParser();
        List<Map<String, String>> attributeList = parser.parse(json);

        return attributeList.stream()
        .map(attributes -> new Content(
            attributes.get("title"), 
            attributes.get("url")))
        .collect(Collectors.toList());
    }

}
