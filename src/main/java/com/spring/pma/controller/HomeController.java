package com.spring.pma.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.spring.pma.dao.iEmployeeRepository;
import com.spring.pma.dao.iProjectRepository;
import com.spring.pma.dto.ChartData;
import com.spring.pma.dto.EmployeeProject;

@Controller
public class HomeController {
	
	@Value("${version}")
	private String ver;
	
	@Autowired
	iProjectRepository proRepo;
	
	@Autowired
	iEmployeeRepository empRepo;
	
	@GetMapping("/")
	public String displayHome(Model model) throws JsonProcessingException {
		List<EmployeeProject> employeeProjCount = empRepo.employeeProject();
		model.addAttribute("employeeList", employeeProjCount);
		
		List<ChartData> projectData = proRepo.getProjectStatus();
		ObjectMapper objectMapper = new ObjectMapper();
		String jsonString =  objectMapper.writeValueAsString(projectData);
		// [["label":"NOTSTARTED", "value":1] , ["label":"INPROGRESS, "value":1], ["label":"COMPLETED", "value":1]]
		
		model.addAttribute("projectStatusCount", jsonString);
		
		model.addAttribute("versionNumber", ver);
		
		return "main/home";
	}

}