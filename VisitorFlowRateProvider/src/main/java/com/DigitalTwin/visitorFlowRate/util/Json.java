package com.DigitalTwin.visitorFlowRate.util;

import com.google.gson.Gson;
import org.springframework.stereotype.Component;

@Component
public class Json {
    private final static Gson gson = new Gson();

    public static String objectToJson(Object object) {
        return gson.toJson(object);
    }
}
