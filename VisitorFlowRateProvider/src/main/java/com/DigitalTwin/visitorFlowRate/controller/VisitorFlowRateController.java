package com.DigitalTwin.visitorFlowRate.controller;

import com.DigitalTwin.visitorFlowRate.service.VisitorFlowRateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SuppressWarnings("all")
@RestController
@RequestMapping("/api/rt/visitorFlowRate")
public class VisitorFlowRateController {
    @Autowired
    private VisitorFlowRateService visitorFlowRateService;

    @GetMapping("/building/{buildingId}/range/{range}/pedestrianflow")
    private String getVisitorFlowRate(@PathVariable String buildingId,
                                      @PathVariable String range) {
        return visitorFlowRateService.getVisitorFlowRateService(buildingId, range);
    }
}
