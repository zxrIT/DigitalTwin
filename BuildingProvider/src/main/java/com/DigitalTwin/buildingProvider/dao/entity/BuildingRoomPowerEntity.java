package com.DigitalTwin.buildingProvider.dao.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.math.BigInteger;
import java.util.Date;

@Data
@TableName("power")
public class BuildingRoomPowerEntity {
    @TableField("roomId")
    private String roomId;

    @TableField("buildingId")
    private String buildingId;

    private int power;
    private Date time;
}
