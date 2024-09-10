package com.DigitalTwin.environment.util;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class ResponseConfig {
    @lombok.Data
    @AllArgsConstructor
    public static class Data {
        private int temperature;
        private int humidity;
        private String time;
    }

    private String title;
    private String buildingId;
    private String roomId;
    private String range;
    private String status;
    private String msg;
    private Data data;
}
