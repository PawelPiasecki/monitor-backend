package com.monitor.model;

import javax.persistence.*;

/**
 * Created by grusz on 19.01.2017.
 */
@Entity
public class System {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int id;
    private String name;
    private String configPath;

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
    @Column(name = "ConfigPath")
    public String getConfigPath() {
        return configPath;
    }

    public void setConfigPath(String configPath) {
        this.configPath = configPath;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        System system = (System) o;

        if (id != system.id) return false;
        if (name != null ? !name.equals(system.name) : system.name != null) return false;
        if (configPath != null ? !configPath.equals(system.configPath) : system.configPath != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (configPath != null ? configPath.hashCode() : 0);
        return result;
    }
}
