package com.DigitalTwin.environment.service.impl;

import com.DigitalTwin.environment.entity.EnvironmentEntity;
import com.DigitalTwin.environment.mapper.EnvironmentMapper;
import com.DigitalTwin.environment.service.EnvironmentService;
import com.DigitalTwin.environment.util.Json;
import com.DigitalTwin.environment.util.ResponseConfig;
import com.DigitalTwin.environment.util.ResponseMessage;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@SuppressWarnings("all")
@Service
public class EnvironmentServiceImpl implements EnvironmentService {
    @Autowired
    EnvironmentMapper environmentMapper;

    @Autowired
    Json json;

    @Override
    public String getEnvironmentService(String buildingId, String roomId, String range) {
        var formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        /** @description 结果返回集 */
        int temperatureResponse = 0, humidityResponse = 0;
        int countHumidity = 0, countTemperature = 0;
        Date databaseNewTimer = null;
        /** @description argument1 range=-1 , timeCache为缓冲时间单位分钟 */
        List<EnvironmentEntity> environmentEntitiesNow = new ArrayList<>();
        LocalDateTime timeCache = now.minus(1, ChronoUnit.MINUTES);
        /** @description argument1 end */
        /** @description argument2 range=range*/
        LocalDateTime rangeMinutesAgo = now.minus(Integer.valueOf(range), ChronoUnit.MINUTES);
        /** @description argument2 end */
        if (roomId.equals("all")) {
            if (range.equals("-1")) {
                List<EnvironmentEntity> environmentEntityList = environmentMapper.selectList(new QueryWrapper<EnvironmentEntity>()
                        .eq("buildingId", buildingId)
                        .between("time", timeCache, now)
                        .orderByDesc("time"));
                databaseNewTimer = environmentEntityList.get(0).getTime();
                for (EnvironmentEntity environmentEntity : environmentEntityList) {
                    if (environmentEntity.getTime().equals(databaseNewTimer)) {
                        environmentEntitiesNow.add(environmentEntity);
                        countTemperature += environmentEntity.getTemperature();
                        countHumidity += environmentEntity.getHumidity();
                    }
                }
                temperatureResponse = countTemperature / environmentEntitiesNow.size();
                humidityResponse = countHumidity / environmentEntitiesNow.size();
            } else {
                List<EnvironmentEntity> environmentEntityList = environmentMapper.selectList(new QueryWrapper<EnvironmentEntity>()
                        .eq("buildingId", buildingId)
                        .between("time", rangeMinutesAgo, now)
                        .orderByDesc("time"));
                databaseNewTimer = environmentEntityList.get(0).getTime();
                for (EnvironmentEntity environmentEntity : environmentEntityList) {
                    countTemperature += environmentEntity.getTemperature();
                    countHumidity += environmentEntity.getHumidity();
                }
                temperatureResponse = countTemperature / environmentEntityList.size();
                humidityResponse = countHumidity / environmentEntityList.size();
            }
        } else {
            if (range.equals("-1")) {
                List<EnvironmentEntity> environmentEntityList = environmentMapper.selectList(new QueryWrapper<EnvironmentEntity>()
                        .eq("buildingId", buildingId)
                        .eq("roomId", roomId)
                        .between("time", timeCache, now)
                        .orderByDesc("time"));
                databaseNewTimer = environmentEntityList.get(0).getTime();
                temperatureResponse = environmentEntityList.get(0).getTemperature();
                humidityResponse = environmentEntityList.get(0).getHumidity();
            } else {
                List<EnvironmentEntity> environmentEntityList =
                        environmentMapper.selectList(new QueryWrapper<EnvironmentEntity>()
                                .eq("buildingId", buildingId)
                                .eq("roomId", roomId)
                                .between("time", rangeMinutesAgo, now)
                                .orderByDesc("time"));
                databaseNewTimer = environmentEntityList.get(0).getTime();
                for (EnvironmentEntity environmentEntity : environmentEntityList) {
                    countTemperature += environmentEntity.getTemperature();
                    countHumidity += environmentEntity.getHumidity();
                }
                temperatureResponse = countTemperature / environmentEntityList.size();
                humidityResponse = countHumidity / environmentEntityList.size();
            }
        }
        return Json.objectToJson(new ResponseConfig("温湿度", buildingId,
                roomId, range, ResponseMessage.statusSuccess, ResponseMessage.messageSuccess,
                new ResponseConfig.Data(temperatureResponse, humidityResponse,
                        formatter.format(databaseNewTimer))));
    }
}
