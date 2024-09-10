package com.DigitalTwin.environment.controller;

import com.DigitalTwin.environment.service.EnvironmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SuppressWarnings("all")
@RestController
@RequestMapping("/api/rt/environment")
public class EnvironmentController {
    @Autowired
    private EnvironmentService environmentService;

    @GetMapping("/building/{buildingId}/room/{roomId}/range/{range}/ht")
    private String getEnvironment(@PathVariable String buildingId,
                                  @PathVariable String roomId,
                                  @PathVariable String range) {
        return environmentService.getEnvironmentService(buildingId, roomId, range);
    }
}
