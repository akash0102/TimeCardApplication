package com.tca.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tca.entity.DateId;
import com.tca.entity.Leave;
@Repository
public interface DateRepository extends JpaRepository<DateId, Integer>{
}
