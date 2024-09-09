package com.DigitalTwin.AllBuildingConsumer.util;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;


@Data
@AllArgsConstructor
public class ResponseConfig {
    @Data
    @AllArgsConstructor
    public static class data {
        private String title;
        private String floor;
        private int totalPower;
        private int proportion;
        private String time;
    }

    private String title;
    private String buildingId;
    private String range;
    private String status;
    private String msg;
    private List<data> data;
}
