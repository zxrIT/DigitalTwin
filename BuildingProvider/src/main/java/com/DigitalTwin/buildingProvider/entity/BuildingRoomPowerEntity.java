package com.DigitalTwin.buildingProvider.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

@Data
@TableName("power")
public class BuildingRoomPowerEntity {
    @TableField("roomId")
    private String roomId;

    @TableField("buildingId")
    private String buildingId;
    private String floor;

    private int power;
    private Date time;
}
