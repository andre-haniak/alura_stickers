package com.alura_stickers;

import java.util.List;

public interface ContentExtractor {
    
    public List<Content> extractContents(String json);
    
}

