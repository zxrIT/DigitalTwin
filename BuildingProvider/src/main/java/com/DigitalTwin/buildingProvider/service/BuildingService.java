package com.DigitalTwin.buildingProvider.service;

import com.DigitalTwin.buildingProvider.util.FeignResponse;

public interface BuildingService {
    String buildingSelectElectricityService(String building, String roomId, String range);

    FeignResponse floorSelectElectricityService(String building, String range, String floor);
}
