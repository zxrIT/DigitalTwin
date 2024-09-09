package com.DigitalTwin.buildingProvider.service.impl;

import com.DigitalTwin.buildingProvider.dao.entity.BuildingRoomPowerEntity;
import com.DigitalTwin.buildingProvider.dao.mapper.BuildingRoomPowerMapper;
import com.DigitalTwin.buildingProvider.service.BuildingService;
import com.DigitalTwin.buildingProvider.util.Json;
import com.DigitalTwin.buildingProvider.util.ResponseConfig;
import com.DigitalTwin.buildingProvider.util.ResponseMessage;
import com.DigitalTwin.buildingProvider.util.Timer;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.List;

@SuppressWarnings("all")
@Service
public class BuildingServiceImpl implements BuildingService {
    @Autowired
    Timer timer;

    @Autowired
    Json json;

    @Autowired
    BuildingRoomPowerMapper buildingRoomPowerMapper;


    @Override
    public String buildingSelectElectricityService(String building, String roomId, String range) {
        int powerCount = 0;
        String currentTimer = timer.getCurrTimer();
        List<BuildingRoomPowerEntity> roomPowerEntityList = null;
        if (!roomId.equals("all")) {
            if (range.equals("1")) {
                roomPowerEntityList = buildingRoomPowerMapper.selectList(
                        ((new QueryWrapper<BuildingRoomPowerEntity>()))
                                .eq("roomId", roomId)
                                .between("time", timer.getStartTimer(), timer.getEndTimer())
                );
                for (BuildingRoomPowerEntity roomPower : roomPowerEntityList) {
                    powerCount += roomPower.getPower();
                }
            }
            else {
                roomPowerEntityList = buildingRoomPowerMapper.selectList(
                        ((new QueryWrapper<BuildingRoomPowerEntity>()))
                                .eq("roomId", roomId)
                                .between("time", timer.getThirtyDaysAgoTime(), timer.getEndTimer())
                );
                for (BuildingRoomPowerEntity roomPower : roomPowerEntityList) {
                    powerCount += roomPower.getPower();
                }
            }
        } else {

        }
        return json.objectToJson(new ResponseConfig("⽤电量统计", building, roomId, range,
                ResponseMessage.statusSuccess, ResponseMessage.messageSuccess,
                new ResponseConfig.data(powerCount, currentTimer)));
    }
}
