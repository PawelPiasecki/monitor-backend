package com.monitor.repository;

import com.monitor.model.System;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/**
 * Created by grusz on 17.12.2016.
 */

@RepositoryRestResource
public interface SystemRepository extends CrudRepository<System,Long>{
}

