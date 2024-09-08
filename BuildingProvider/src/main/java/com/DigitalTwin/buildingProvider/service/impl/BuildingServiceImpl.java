package com.DigitalTwin.buildingProvider.service.impl;

import com.DigitalTwin.buildingProvider.service.BuildingService;
import com.DigitalTwin.buildingProvider.util.Json;
import com.DigitalTwin.buildingProvider.util.ResponseConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@SuppressWarnings("all")
@Service
public class BuildingServiceImpl implements BuildingService {
    @Autowired
    Json json;

    @Override
    public String buildingSelectElectricityService(String building, String roomId, String range) {
        return json.objectToJson(new ResponseConfig("⽤电量统计", "nxist0002", "1", "30", "success", "msg",
                new ResponseConfig.ResponseData(354, "2024-11-25 18:09:25")));
    }
}
