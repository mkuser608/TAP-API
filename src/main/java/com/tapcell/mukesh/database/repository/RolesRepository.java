package com.tapcell.mukesh.database.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.tapcell.mukesh.database.entity.Roles;

@Repository
public interface RolesRepository extends CrudRepository<Roles, String>{

}
