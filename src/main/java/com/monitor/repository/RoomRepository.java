package com.monitor.repository;

import com.monitor.model.Room;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/**
 * Created by grusz on 17.12.2016.
 */

@RepositoryRestResource
public interface RoomRepository extends CrudRepository<Room,Long>{
}

