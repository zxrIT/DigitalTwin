package com.DigitalTwin.AllBuildingConsumer.controller;

import com.DigitalTwin.AllBuildingConsumer.service.AllBuildingConsumerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SuppressWarnings("all")
@RestController
@RequestMapping("/api/rt/floor")
public class AllBuildingConsumerController {
    @Autowired
    AllBuildingConsumerService allBuildingConsumerService;

    @GetMapping("/building/{buildingId}/range/{range}/power/proportion")
    private String getBuildingPower(@PathVariable("buildingId") String building,
                                    @PathVariable("range") String range) {
        return allBuildingConsumerService.getBuildingPowerService(building, range);
    }
}
