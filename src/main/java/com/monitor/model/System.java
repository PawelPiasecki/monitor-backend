package com.monitor.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Created by grusz on 16.12.2016.
 */
@Entity
@Table(name="SYSTEMS")
public class System {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="SYSTEM_ID")
    private long systemID;

    @Column(name="NAME")
    private String name;
    @Column(name="INFO")
    private String info;
    @Column(name="LOCALIZATION")
    private String localization;

    @OneToMany(mappedBy = "system")
    Set<Room> rooms;



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getLocalization() {
        return localization;
    }

    public void setLocalization(String localization) {
        this.localization = localization;
    }


}
