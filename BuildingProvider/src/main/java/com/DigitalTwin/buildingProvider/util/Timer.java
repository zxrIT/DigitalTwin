package com.DigitalTwin.buildingProvider.util;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.Date;

@Component
@Data
public class Timer {
    private Date currentTimer = new Date();
    private LocalDateTime startTimer = LocalDate.now().atStartOfDay();
    private LocalDateTime endTimer = LocalDateTime.now().with(LocalTime.MAX);

    private LocalDate nowTime = LocalDate.now();
    private LocalDate thirtyDaysAgoTime = nowTime.minus(30, ChronoUnit.DAYS);


    public String getCurrTimer() {
        var formatter = new SimpleDateFormat("y-M-d H:m:s");
        return formatter.format(currentTimer);
    }
}
