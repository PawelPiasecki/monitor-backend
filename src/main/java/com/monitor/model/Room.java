package com.monitor.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Created by grusz on 16.12.2016.
 */
@Entity
@Table(name="ROOMS")
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="ROOM_ID")
    private long roomID;

    @Column(name="NAME")
    private String name;

    @OneToMany(mappedBy = "room")
    Set<Sensor> sensors;

    @ManyToOne
    private System system;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

