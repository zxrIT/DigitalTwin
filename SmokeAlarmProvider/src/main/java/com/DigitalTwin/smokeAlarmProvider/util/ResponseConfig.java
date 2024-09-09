package com.DigitalTwin.smokeAlarmProvider.util;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class ResponseConfig {

    @lombok.Data
    @lombok.AllArgsConstructor
    public static class Data {
        private String alarmSensorId;
        private String title;
        private String roomId;
        private boolean triggered;
        private String time;
    }

    private String title;
    private String buildingId;
    private String range;
    private String status;
    private String msg;
    private List<Data> data;
}
