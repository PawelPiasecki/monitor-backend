package com.monitor.repository;

import com.monitor.model.Sensor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

/**
 * Created by grusz on 17.12.2016.
 */

@RepositoryRestResource
public interface SensorRepository extends CrudRepository<Sensor,Long>{

    List<Sensor> findByName(String name);

    List<Sensor> findByIsActiveAndSystem(boolean isActive, com.monitor.model.System system);

    List<Sensor> findBySystem(com.monitor.model.System system);
}
