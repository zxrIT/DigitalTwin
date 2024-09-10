package com.DigitalTwin.visitorFlowRate.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
@TableName("visitor")
public class VisitorFlowRateEntity {
    @TableField("buildingId")
    private String buildingId;
    @TableField("pedestrianFlow")
    private int pedestrianFlow;
    private Date time;
}
