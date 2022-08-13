package com.bec.mqttserver.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 设备表
 */
@Entity
@Getter
@Setter
@ToString
@Table(name = "device")
public class Device extends AbstractEntity {

    // 空闲中
    public static final int STATUS_IDLE = 0;
    // 使用中
    public static final int STATUS_USING = 1;

    // 关机
    public static final int RUNNING_STATUS_OFF = 0;
    // 开机使用中
    public static final int RUNNING_STATUS_ON = 1;
    // 出现故障
    public static final int RUNNING_STATUS_ERROR = 2;

    //设备编号
    @Column(name = "device_id")
    private String deviceId = "";

    //使用此设备的用户
    @Column(name = "user_id")
    private long userId;

    //设备状态
    @Column(name = "status")
    private int status = STATUS_IDLE;


    //运行状态
    @Column(name = "running_status")
    private int runningStatus = RUNNING_STATUS_ON;

    //经度
    @Column(name = "longitude")
    private String longitude = "";

    //纬度
    @Column(name = "latitude")
    private String latitude = "";

    //错误码
    @Column(name = "code")
    private String errorCode = "";

    //统计方式
    @Column(name = "statistic_type")
    private String statisticType = "";

    //统计时间
    @Column(name = "statistic_date")
    private String statisticDate = "";

}
