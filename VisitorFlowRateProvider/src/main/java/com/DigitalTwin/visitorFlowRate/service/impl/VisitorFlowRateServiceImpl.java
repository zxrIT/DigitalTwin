package com.DigitalTwin.visitorFlowRate.service.impl;

import com.DigitalTwin.visitorFlowRate.entity.VisitorFlowRateEntity;
import com.DigitalTwin.visitorFlowRate.mapper.VisitorFlowRateMapper;
import com.DigitalTwin.visitorFlowRate.service.VisitorFlowRateService;
import com.DigitalTwin.visitorFlowRate.util.Json;
import com.DigitalTwin.visitorFlowRate.util.ResponseConfig;
import com.DigitalTwin.visitorFlowRate.util.ResponseMessage;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

@SuppressWarnings("all")
@Service
public class VisitorFlowRateServiceImpl implements VisitorFlowRateService {
    @Autowired
    VisitorFlowRateMapper visitorFlowRateMapper;

    @Autowired
    Json json;

    @Override
    public String getVisitorFlowRateService(String buildingId, String range) {
        int visitorCount = 0;
        var formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime rangeMinutesAgo = now.minus(Integer.valueOf(range), ChronoUnit.MINUTES);
        List<VisitorFlowRateEntity> visitorFlowRateList = visitorFlowRateMapper.selectList(new QueryWrapper<VisitorFlowRateEntity>()
                .eq("buildingId", buildingId)
                .between("time", rangeMinutesAgo, now)
                .orderByAsc("time"));
        for (VisitorFlowRateEntity visitorFlowRateEntity : visitorFlowRateList) {
            visitorCount += visitorFlowRateEntity.getPedestrianFlow();
        }
        return Json.objectToJson(new ResponseConfig("⼈流量", buildingId, range,
                ResponseMessage.statusSuccess, ResponseMessage.messageSuccess,
                new ResponseConfig.Data(visitorCount,
                        formatter.format(visitorFlowRateList.get(visitorFlowRateList.size() - 1).getTime()))
        ));
    }
}
