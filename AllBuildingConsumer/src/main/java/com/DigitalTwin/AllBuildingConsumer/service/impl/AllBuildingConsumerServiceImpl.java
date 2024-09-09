package com.DigitalTwin.AllBuildingConsumer.service.impl;

import com.DigitalTwin.AllBuildingConsumer.fegin.BuildingClients;
import com.DigitalTwin.AllBuildingConsumer.service.AllBuildingConsumerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@SuppressWarnings("all")
@Service
public class AllBuildingConsumerServiceImpl implements AllBuildingConsumerService {
    @Autowired
    private BuildingClients buildingClients;

    @Override
    public String getBuildingPowerService(String building, String range) {
        String buildingSelectElectricityOneFloor = buildingClients.buildingSelectElectricity(building, "all", range);
        System.out.println(buildingSelectElectricityOneFloor);
        return null;
    }
}
