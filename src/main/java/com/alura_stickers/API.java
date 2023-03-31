package com.alura_stickers;

public enum API {
    TOP_LANGUAGES("http://localhost:8080/languages", new LanguageContentExtractor()),
    IMDB_TOP_MOVIES("https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/TopMovies.json", new ImdbContentExtractor()),
    NASA_APOD("https://api.nasa.gov/planetary/apod?api_key=DEMO_KEY&start_date=2023-02-09&end_date=2023-02-15", new NasaContentExtractor());

    private String url;
    private ContentExtractor extractor;


    API(String url, ContentExtractor extractor) {
        this.url = url;
        this.extractor = extractor;
    }

    public String getUrl(){
        return url;
    }

    public ContentExtractor getExtractor() {
        return extractor;
    }
}
