package com.DigitalTwin.visitorFlowRate.util;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ResponseConfig {
    @lombok.Data
    @AllArgsConstructor
    public static class Data {
        private int pedestrianFlow;
        private String time;
    }

    private String title;
    private String buildingId;
    private String range;
    private String status;
    private String msg;
    private Data data;
}
