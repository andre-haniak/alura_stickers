package com.alura_stickers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class JsonParser {

    private static final Pattern REGEX_ITEMS = Pattern.compile(".*\\[(.+)\\].*");

    private static final Pattern REGEX_ATTRIBUTES_JSON = Pattern.compile("\"(.+?)\":\"(.*?)\"");
    
    public List<Map<String, String>> parse(String json) {

        Matcher matcher = REGEX_ITEMS.matcher(json);
        if (!matcher.find()) {

            throw new IllegalArgumentException("Não encontrou items.");
        }

        String[] items = matcher.group(1).split("\\},\\{");

        List<Map<String, String>> data = new ArrayList<>();

        for (String item : items) {

            Map<String, String> ATTRIBUTESItem = new HashMap<>();

            Matcher matcherATTRIBUTESJson = REGEX_ATTRIBUTES_JSON.matcher(item);
            while (matcherATTRIBUTESJson.find()) {
                String attribute = matcherATTRIBUTESJson.group(1);
                String value = matcherATTRIBUTESJson.group(2);
                ATTRIBUTESItem.put(attribute, value);
            }

            data.add(ATTRIBUTESItem);
        }

        return data;
    }
}
