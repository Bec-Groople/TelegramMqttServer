package com.bec.mqttserver.model;


import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;

/**
 * 所有类的超类
 * 自动更新创建时间和更新时间
 *
 * @author peter
 **/
@MappedSuperclass
@Getter
@Setter
@EntityListeners(value = AuditingEntityListener.class)
public class AbstractEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @CreatedDate
    @Column(name = "create_time", updatable = false)
    private long createTime;


    @LastModifiedDate
    @Column(name = "update_time")
    private long updateTime;

}
