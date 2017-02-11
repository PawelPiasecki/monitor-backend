package com.monitor.controller;

import com.monitor.model.Sensor;

import java.util.List;

/**
 * Created by artur on 2/10/17.
 */
public class SensorFormWrapper {

    private List<Sensor> sensors;

    public List<Sensor> getSensors() {
        return sensors;
    }

    public void setSensors(List<Sensor> sensors) {
        this.sensors = sensors;
    }
}
