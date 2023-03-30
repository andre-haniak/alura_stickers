package com.alura_stickers;

import java.util.Map;

public record Content (String title, String urlImage) {

    public static Content toContent(Map<String, String> map) {
        return new Content(map.get("title"), map.get("image"));
    }
}