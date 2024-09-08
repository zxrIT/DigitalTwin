package com.DigitalTwin.buildingProvider.controller;

import com.DigitalTwin.buildingProvider.service.BuildingService;
import com.DigitalTwin.buildingProvider.util.Json;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@SuppressWarnings("all")
@RestController
@RequestMapping("/api/rt")
public class BuildingController {

    @Autowired
    BuildingService buildingService;


    @GetMapping("/building/{building}/room/{roomId}/range/{range}/power/analysis")
    private String buildingSelectElectricity(@PathVariable("building") String building,
                                             @PathVariable("roomId") String roomId,
                                             @PathVariable("range") String range) {
        return buildingService.buildingSelectElectricityService(building, roomId, range);
    }
}
