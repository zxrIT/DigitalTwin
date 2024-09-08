package com.DigitalTwin.buildingProvider.util;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@AllArgsConstructor
public class ResponseConfig {
    @Data
    @AllArgsConstructor
    public static class ResponseData {
        private Integer power;
        private String time;
    }

    private String title;
    private String buildingId;
    private String roomId;
    private String range;
    private String status;
    private String msg;
    private ResponseData responseData;
}
