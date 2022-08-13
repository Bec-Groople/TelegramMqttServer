package com.bec.mqttserver.dao;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.bec.mqttserver.model.Device;

@Repository
public interface IDeviceDao extends PagingAndSortingRepository<Device, Long> {

    Device findByDeviceId(String deviceId);
 
}
