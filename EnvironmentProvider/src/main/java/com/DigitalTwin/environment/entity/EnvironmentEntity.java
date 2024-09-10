package com.DigitalTwin.environment.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
@TableName("env")
public class EnvironmentEntity {
    @TableField("buildingId")
    private String buildingId;

    @TableField("roomId")
    private String roomId;

    private int temperature;
    private int humidity;
    private Date time;
}
