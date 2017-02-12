package com.monitor.repository;

import com.monitor.model.Room;
import com.monitor.model.Sensor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

/**
 * Created by grusz on 17.12.2016.
 */

@RepositoryRestResource
public interface  RoomRepository extends CrudRepository<Room,Integer>{

    List<Room> findByName(String name);

    List<Room> findBySystem(com.monitor.model.System system);
}

