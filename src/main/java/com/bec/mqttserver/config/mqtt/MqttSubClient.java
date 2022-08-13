package com.bec.mqttserver.config.mqtt;

import lombok.extern.slf4j.Slf4j;

import static com.bec.mqttserver.config.mqtt.MqttConfiguration.*;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class MqttSubClient {

    public MqttSubClient(MqttPushClient mqttPushClient) {
        subScribeDataPublishTopic();
    }


    private void subScribeDataPublishTopic() {
        //订阅test_queue主题
        subscribe(TOPIC_ACTIVE_QUERY);
        subscribe(TOPIC_ACTIVE_QUERY_RESULT);
        subscribe(TOPIC_DEVICE_STATISTICS);
    }

    /**
     * 订阅某个主题，qos默认为0
     *
     * @param topic
     */
    public void subscribe(String topic) {
        subscribe(topic, 0);
    }

    /**
     * 订阅某个主题
     *
     * @param topic 主题名
     * @param qos
     */
    public void subscribe(String topic, int qos) {
        try {
            MqttClient client = MqttPushClient.getClient();
            if (client == null) return;
            client.subscribe(topic, qos);
            log.info("订阅主题:{}", topic);
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }
}

