package com.DigitalTwin.AllBuildingConsumer.service.impl;

import com.DigitalTwin.AllBuildingConsumer.fegin.BuildingClients;
import com.DigitalTwin.AllBuildingConsumer.service.AllBuildingConsumerService;
import com.DigitalTwin.AllBuildingConsumer.util.FeignResponse;
import com.DigitalTwin.AllBuildingConsumer.util.Json;
import com.DigitalTwin.AllBuildingConsumer.util.ResponseConfig;
import com.DigitalTwin.AllBuildingConsumer.util.ResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("all")
@Service
public class AllBuildingConsumerServiceImpl implements AllBuildingConsumerService {
    @Autowired
    private BuildingClients buildingClients;

    @Autowired
    Json json;

    @Override
    public String getBuildingPowerService(String building, String range) {
        float allFloorPower = 0;
        FeignResponse buildingSelectElectricityOneOfFloor = null;
        List<FeignResponse> feignResponseList = new ArrayList<>();
        List<ResponseConfig.data> responseConfigDataList = new ArrayList<>();
        for (int i = 1; i < 5; i++) {
            buildingSelectElectricityOneOfFloor = buildingClients.buildingSelectElectricity(
                    building, "all", String.valueOf(i).format("%d", i)
            );
            allFloorPower += buildingSelectElectricityOneOfFloor.getTotalPower();
            feignResponseList.add(buildingSelectElectricityOneOfFloor);
        }
        for (FeignResponse feignResponse : feignResponseList) {
            responseConfigDataList.add(new ResponseConfig.data(
                    feignResponse.getTitle(),
                    feignResponse.getFloor(),
                    feignResponse.getTotalPower(),
                    (int) (((feignResponse.getTotalPower() / allFloorPower) * 100) + 1),
                    feignResponse.getTime()
            ));
        }
        return Json.objectToJson(new ResponseConfig("⽤电量统计", building, range,
                ResponseMessage.statusSuccess, ResponseMessage.statusError, responseConfigDataList)
        );
    }
}
