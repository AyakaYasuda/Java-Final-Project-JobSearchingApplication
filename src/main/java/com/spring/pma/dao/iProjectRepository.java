package com.spring.pma.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.spring.pma.dto.ChartData;
import com.spring.pma.entity.Project;

public interface iProjectRepository extends CrudRepository<Project, Long> {
	
	@Override
	public List<Project> findAll();

	@Query(nativeQuery = true, value = "SELECT stage as label, COUNT(*) as value "
			+ "FROM project "
			+ "GROUP BY stage;")
	public List<ChartData> getProjectStatus();
	
	@Query(nativeQuery = true, value = "SELECT project_id, name, stage, description "
			+ "FROM project "
			+ "WHERE project.description = ?1 "			
			+ ";")
	public List<Project> getProjectsWithTheSkill(String skill);
}


