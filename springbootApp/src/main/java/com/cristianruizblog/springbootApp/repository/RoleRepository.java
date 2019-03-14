package com.cristianruizblog.springbootApp.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.cristianruizblog.springbootApp.entity.Role;

@Repository
public interface RoleRepository extends CrudRepository<Role, Long>{

}
