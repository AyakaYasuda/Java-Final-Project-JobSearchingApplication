package com.spring.pma.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import com.spring.pma.entity.Job;

public interface iJobRepository extends CrudRepository<Job, Long> {
	
	@Override
	public List<Job> findAll();

}
