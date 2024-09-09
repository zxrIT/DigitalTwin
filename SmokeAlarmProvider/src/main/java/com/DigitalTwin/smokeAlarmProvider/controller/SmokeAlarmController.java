package com.DigitalTwin.smokeAlarmProvider.controller;

import com.DigitalTwin.smokeAlarmProvider.service.SmokeAlarmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SuppressWarnings("all")
@RequestMapping("/api/rt/smoke")
public class SmokeAlarmController {
    @Autowired
    private SmokeAlarmService smokeAlarmService;

    @GetMapping("/building/{buildingId}/alarm/smoke")
    private String getSmokeAlarm(@PathVariable String buildingId) {
        return smokeAlarmService.getSmokeAlarmService(buildingId);
    }
}
