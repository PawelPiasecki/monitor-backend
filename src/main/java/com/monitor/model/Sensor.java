package com.monitor.model;

import javax.persistence.*;

/**
 * Created by grusz on 19.01.2017.
 */
@Entity
public class Sensor {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int id;
    private String name;
    private String socketUrl;
    private boolean isActive;
    private String actualStatus;

    @Id
    @Column(name = "Id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "Name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "SocketUrl")
    public String getSocketUrl() {
        return socketUrl;
    }

    public void setSocketUrl(String socketUrl) {
        this.socketUrl = socketUrl;
    }

    @Basic
    @Column(name = "IsActive")
    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    @Basic
    @Column(name = "ActualStatus")
    public String getActualStatus() {
        return actualStatus;
    }

    public void setActualStatus(String actualStatus) {
        this.actualStatus = actualStatus;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Sensor sensor = (Sensor) o;

        if (id != sensor.id) return false;
        if (isActive != sensor.isActive) return false;
        if (name != null ? !name.equals(sensor.name) : sensor.name != null) return false;
        if (socketUrl != null ? !socketUrl.equals(sensor.socketUrl) : sensor.socketUrl != null) return false;
        if (actualStatus != null ? !actualStatus.equals(sensor.actualStatus) : sensor.actualStatus != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (socketUrl != null ? socketUrl.hashCode() : 0);
        result = 31 * result + (isActive ? 1 : 0);
        result = 31 * result + (actualStatus != null ? actualStatus.hashCode() : 0);
        return result;
    }
}
