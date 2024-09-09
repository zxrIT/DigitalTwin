package com.DigitalTwin.AllBuildingConsumer.util;

import lombok.AllArgsConstructor;
import lombok.Data;


@AllArgsConstructor
@Data
public class FeignResponse {
    private String title;
    private String floor;
    private int totalPower;
    private String time;
}
