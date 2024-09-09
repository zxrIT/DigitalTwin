package com.DigitalTwin.buildingProvider.util;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;


@Data
@AllArgsConstructor
public class ResponseConfig {
    @Data
    @AllArgsConstructor
    public static class data {
        private int power;
        private String time;
    }

    private String title;
    private String buildingId;
    private String roomId;
    private String range;
    private String status;
    private String msg;
    private data data;
}
