package com.monitor.model;

import javax.persistence.*;

/**
 * Created by grusz on 16.12.2016.
 */
@Entity
@Table(name="SENSORS")
public class Sensor {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="SENSOR_ID")
    private long sensorID;

    @Column(name="NAME")
    private String name;

    @ManyToOne
    private Room room;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
