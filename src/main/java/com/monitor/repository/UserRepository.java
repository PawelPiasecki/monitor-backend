package com.monitor.repository;

import com.monitor.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/**
 * Created by artur on 1/29/17.
 */
@RepositoryRestResource
public interface UserRepository extends CrudRepository<User, Long> {
}
