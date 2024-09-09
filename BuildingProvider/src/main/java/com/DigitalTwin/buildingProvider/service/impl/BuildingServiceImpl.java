package com.DigitalTwin.buildingProvider.service.impl;

import com.DigitalTwin.buildingProvider.dao.entity.BuildingRoomPowerEntity;
import com.DigitalTwin.buildingProvider.dao.mapper.BuildingRoomPowerMapper;
import com.DigitalTwin.buildingProvider.service.BuildingService;
import com.DigitalTwin.buildingProvider.util.*;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
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
        var formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        int powerCount = 0;
        List<BuildingRoomPowerEntity> roomPowerEntityList = null;
        if (!roomId.equals("all")) {
            if (range.equals("1")) {
                roomPowerEntityList = buildingRoomPowerMapper.selectList(
                        ((new QueryWrapper<BuildingRoomPowerEntity>()))
                                .eq("buildingId", building)
                                .eq("roomId", roomId)
                                .between("time", timer.getStartTimer(), timer.getEndTimer())
                );
                for (BuildingRoomPowerEntity roomPower : roomPowerEntityList) {
                    powerCount += roomPower.getPower();
                }
            } else {
                roomPowerEntityList = buildingRoomPowerMapper.selectList(
                        ((new QueryWrapper<BuildingRoomPowerEntity>()))
                                .eq("buildingId", building)
                                .eq("roomId", roomId)
                                .between("time", timer.getThirtyDaysAgoTime(), timer.getEndTimer())
                );
                for (BuildingRoomPowerEntity roomPower : roomPowerEntityList) {
                    powerCount += roomPower.getPower();
                }
            }
        } else {
            if (range.equals("1")) {
                roomPowerEntityList = buildingRoomPowerMapper.selectList(
                        ((new QueryWrapper<BuildingRoomPowerEntity>()))
                                .eq("buildingId", building)
                                .between("time", timer.getStartTimer(), timer.getEndTimer())
                );
                for (BuildingRoomPowerEntity roomPower : roomPowerEntityList) {
                    powerCount += roomPower.getPower();
                }
            } else {
                roomPowerEntityList = buildingRoomPowerMapper.selectList(
                        ((new QueryWrapper<BuildingRoomPowerEntity>()))
                                .eq("buildingId", building)
                                .between("time", timer.getThirtyDaysAgoTime(), timer.getEndTimer())
                );
                for (BuildingRoomPowerEntity roomPower : roomPowerEntityList) {
                    powerCount += roomPower.getPower();
                }
            }
        }
        return json.objectToJson(new ResponseConfig("⽤电量统计", building, roomId, range,
                ResponseMessage.statusSuccess, ResponseMessage.messageSuccess,
                new ResponseConfig.data(powerCount,
                        formatter.format(roomPowerEntityList.get(roomPowerEntityList.size() - 1).getTime())
                )));
    }

    @Override
    public FeignResponse floorSelectElectricityService(String building, String range, String floor) {
        var formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        int powerCount = 0, proportion = 0;
        List<BuildingRoomPowerEntity> roomPowerEntityList = null;
        if (range.equals("1")) {
            roomPowerEntityList = buildingRoomPowerMapper.selectList(
                    ((new QueryWrapper<BuildingRoomPowerEntity>()))
                            .eq("buildingId", building)
                            .eq("floor", floor)
                            .between("time", timer.getStartTimer(), timer.getEndTimer())
            );
            for (BuildingRoomPowerEntity roomPower : roomPowerEntityList) {
                powerCount += roomPower.getPower();
            }
        } else {
            roomPowerEntityList = buildingRoomPowerMapper.selectList(
                    ((new QueryWrapper<BuildingRoomPowerEntity>()))
                            .eq("buildingId", building)
                            .eq("floor", floor)
                            .between("time", timer.getThirtyDaysAgoTime(), timer.getEndTimer())
            );
            for (BuildingRoomPowerEntity roomPower : roomPowerEntityList) {
                powerCount += roomPower.getPower();
            }
        }
        return new FeignResponse(
                String.format("新⼯科%s号楼第%s层⽤电量", building, floor),
                floor, powerCount,
                formatter.format(roomPowerEntityList.get(roomPowerEntityList.size() - 1).getTime()));
    }
}
