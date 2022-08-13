package com.bec.mqttserver.config.mqtt;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;
import com.bec.mqttserver.dao.IDeviceDao;
import com.bec.mqttserver.model.Device;
import com.bec.mqttserver.util.TextUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class PushCallback implements MqttCallback {

    @Autowired
    private MqttConfiguration mqttConfiguration;

    @Autowired
    private IDeviceDao mDeviceDao;

    @Override
    public void connectionLost(Throwable cause) {        // 连接丢失后，一般在这里面进行重连
        log.info("连接断开，正在重连");
        MqttPushClient mqttPushClient = mqttConfiguration.getMqttPushClient();
        if (null != mqttPushClient) {
            mqttPushClient.connect(mqttConfiguration.getHost(), mqttConfiguration.getClientid(), mqttConfiguration.getUsername(), mqttConfiguration.getPassword(), mqttConfiguration.getTimeout(), mqttConfiguration.getKeepalive());
            log.info("已重连");
        }

    }

    /**
     * 发送消息，消息到达后处理方法
     *
     * @param token
     */
    @Override
    public void deliveryComplete(IMqttDeliveryToken token) {
        log.info("消息已发送 : " + token);
    }


    /**
     * 订阅主题接收到消息处理方法
     *
     * @param topic
     * @param message
     */
    @Override
    public void messageArrived(String topic, MqttMessage message) {
        // subscribe后得到的消息会执行到这里面,这里在控制台有输出
        log.info("接收消息主题 : " + topic);
        log.info("接收消息Qos : " + message.getQos());
        log.info("接收消息内容 : " + new String(message.getPayload()));
        String payload = new String(message.getPayload());
        if (topic.startsWith("active_query/")) {
            activeQuery(payload);
            return;
        }
        if (topic.startsWith("device_statistics/")) {
            updateDeviceStatus(payload);
            return;
        }
    }

    /**
     * 查询设备是否被激活
     *
     * @param payload 参数
     */
    public void activeQuery(String payload) {
        log.info("activeQuery--- >: " + payload);
        JSONObject result = new JSONObject();
        MqttPushClient mqttPushClient = mqttConfiguration.getMqttPushClient();
        int status = 0;
        try {
            JSONObject jsonObject = JSONObject.parseObject(payload);
            String deviceId = jsonObject.getString("device_id");
            Device device = mDeviceDao.findByDeviceId(deviceId);
            log.info("activeQuery,device--- >: " + deviceId + "," + device);
            if (device != null) {
                status = device.getStatus();
            }
            result.put("status", status);
            String targetTopic = "active_query_result/" + deviceId;
            mqttPushClient.publish(1, false, targetTopic, result.toJSONString());
        } catch (Exception e) {
            log.error("activeQuery出错:" + e.getMessage());
        }
    }


    /**
     * 更新设备信息
     *
     * @param payload 参数
     */
    private void updateDeviceStatus(String payload) {
        try {
            JSONObject jsonObject = JSONObject.parseObject(payload);
            String deviceId = jsonObject.getString("device_id");
            String location = jsonObject.getString("location");
            String[] lngLat = location.split(",");
            String type = jsonObject.getString("type");
            String errorCode = jsonObject.getString("errorCode");
            String date = jsonObject.getString("date");
            String status = jsonObject.getString("status");
            Device device = mDeviceDao.findByDeviceId(deviceId);
            if (device == null) {
                log.info("设备不存在:" + payload);
                return;
            }
            int runningStatus = 0;
            switch (status) {
                case "on":
                    runningStatus = 1;
                    break;
                case "off":
                    runningStatus = 2;
                    break;
                case "error":
                    runningStatus = 3;
                    break;
            }
            device.setRunningStatus(runningStatus);
            device.setStatisticType(type);
            device.setStatisticDate(date);
            if (!TextUtil.isEmpty(errorCode)) {
                device.setErrorCode(errorCode);
            }
            if (lngLat.length == 2) {
                device.setLongitude(lngLat[0]);
                device.setLatitude(lngLat[1]);
            }
            mDeviceDao.save(device);
        } catch (Exception e) {
            log.error("updateDeviceStatus出错:" + e.getMessage());
        }
    }


}
