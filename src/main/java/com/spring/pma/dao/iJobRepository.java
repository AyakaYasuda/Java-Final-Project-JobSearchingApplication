package com.spring.pma.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import com.spring.pma.entity.Job;
import com.spring.pma.entity.Project;

public interface iJobRepository extends CrudRepository<Job, Long> {
	
	@Override
	public List<Job> findAll();
	
	@Query(nativeQuery = true, value = "SELECT job_id, title, company, location, description, skill "
			+ "FROM job "
			+ "WHERE job.skill = ?1 "			
			+ ";")
	public List<Job> getJobsWithTheSkill(String skill);

}
