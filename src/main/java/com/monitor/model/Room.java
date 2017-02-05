package com.monitor.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Created by grusz on 16.12.2016.
 */
@Entity
@Table(name = "ROOMS")
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ROOM_ID")
    private long roomID;

    @Column(name = "NAME")
    private String name;

    @OneToMany(mappedBy = "room")
    @JsonIgnore
    Set<Sensor> sensors;

    @ManyToOne
    @JsonIgnore
    private System system;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getRoomID() {
        return roomID;
    }

    public void setRoomID(long roomID) {
        this.roomID = roomID;
    }

    public Set<Sensor> getSensors() {
        return sensors;
    }

    public void setSensors(Set<Sensor> sensors) {
        this.sensors = sensors;
    }

    public System getSystem() {
        return system;
    }

    public void setSystem(System system) {
        this.system = system;
    }
}

