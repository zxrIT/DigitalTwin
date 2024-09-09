package com.DigitalTwin.smokeAlarmProvider.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
@TableName("smoke")
public class SmokeAlarmEntity {
    @TableField("alarmSensorId")
    private String alarmSensorId;
    @TableField("roomId")
    private String roomId;

    private int triggered;
    private Date time;
}
