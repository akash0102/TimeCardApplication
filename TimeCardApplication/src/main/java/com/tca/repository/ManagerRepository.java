package com.tca.repository;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.tca.entity.Manager;


@Repository
public interface ManagerRepository extends  JpaRepository<Manager, Integer>  {

//	@Query("select t from Manager t where t.email=:email")
//    Manager findByEmail(String email);

	
}
