package com.DigitalTwin.smokeAlarmProvider.service.impl;

import com.DigitalTwin.smokeAlarmProvider.entity.SmokeAlarmEntity;
import com.DigitalTwin.smokeAlarmProvider.mapper.SmokeAlarmMapper;
import com.DigitalTwin.smokeAlarmProvider.service.SmokeAlarmService;
import com.DigitalTwin.smokeAlarmProvider.util.Json;
import com.DigitalTwin.smokeAlarmProvider.util.ResponseConfig;
import com.DigitalTwin.smokeAlarmProvider.util.ResponseMessage;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SuppressWarnings("all")
@Service
public class SmokeAlarmServiceImpl implements SmokeAlarmService {
    @Autowired
    SmokeAlarmMapper smokeAlarmMapper;

    @Autowired
    Json json;

    @Override
    public String getSmokeAlarmService(String buildingId) {
        List<ResponseConfig.Data> responseData = new ArrayList<>();
        Map<String, List<SmokeAlarmEntity>> smokeAlarmMap = new HashMap<>();
        var formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime thirtyMinutesAgo = now.minus(30, ChronoUnit.MINUTES);
        List<SmokeAlarmEntity> smokeAlarmList =
                smokeAlarmMapper.selectList(new QueryWrapper<SmokeAlarmEntity>()
                        .eq("buildingId", buildingId)
                        .between("time", thirtyMinutesAgo, now)
                        .orderByAsc("time"));
        for (SmokeAlarmEntity smokeAlarmEntity : smokeAlarmList) {
            if (!smokeAlarmMap.containsKey(smokeAlarmEntity.getRoomId())) {
                smokeAlarmMap.put(smokeAlarmEntity.getRoomId(), new ArrayList<>());
                smokeAlarmMap.get(smokeAlarmEntity.getRoomId()).add(smokeAlarmEntity);
            } else {
                smokeAlarmMap.get(smokeAlarmEntity.getRoomId()).add(smokeAlarmEntity);
            }
        }
        smokeAlarmMap.forEach((key, value) -> {
            boolean triggered = false;
            String alarmSensorId = null;
            String roomId = null;
            String time = null;
            for (SmokeAlarmEntity smokeAlarmEntity : value) {
                alarmSensorId = smokeAlarmEntity.getAlarmSensorId();
                roomId = smokeAlarmEntity.getRoomId();
                time = formatter.format(smokeAlarmEntity.getTime());
                triggered = triggered != true ? smokeAlarmEntity.getTriggered() != 0 : true;
            }
            responseData.add(new ResponseConfig.Data(alarmSensorId,
                    String.format("%s室烟雾报警器", roomId), roomId, triggered, time));
        });
        return Json.objectToJson(new ResponseConfig(
                "烟雾报警器状态", buildingId, "30",
                ResponseMessage.statusSuccess, "烟雾报警器实时数据",
                responseData
        ));
    }
}
