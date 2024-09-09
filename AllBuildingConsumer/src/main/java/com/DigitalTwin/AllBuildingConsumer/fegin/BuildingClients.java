package com.DigitalTwin.AllBuildingConsumer.fegin;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient("BuildingProvider")
public interface BuildingClients {

    @GetMapping("/api/rt/building/{building}/floor/{floor}/range/{range}/power/proportion")
    String buildingSelectElectricity(@PathVariable String building,
                                     @PathVariable String range,
                                     @PathVariable String floor);
}

