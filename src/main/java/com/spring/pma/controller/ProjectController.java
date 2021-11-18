package com.spring.pma.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.spring.pma.dao.iEmployeeRepository;
import com.spring.pma.dao.iProjectRepository;
import com.spring.pma.entity.Employee;
import com.spring.pma.entity.Project;

@Controller
@RequestMapping("/projects")
public class ProjectController {
	
	@Autowired
	iProjectRepository proRepo;
	
	@Autowired
	iEmployeeRepository empRepo;

	@GetMapping
	public String displayProjects(Model model) {
		List<Project> projects = proRepo.findAll();
		model.addAttribute("projectList", projects);
		return "projects/list-projects";
	}
	
//	@RequestMapping("/new")
	@GetMapping("/new")
	public String displayProjectForm(Model model) {
		List<Employee> employees = empRepo.findAll();
		System.out.println(employees);
		model.addAttribute("employeeList", employees);
		
		model.addAttribute("project", new Project());
		return "projects/new-project";
	}
	
//	@RequestMapping(value="/save", method = RequestMethod.POST)
	@PostMapping("/save")
	public String createProject(Project project, Model model) {
		//this should handle saving to the database
		proRepo.save(project);
	
		
		return "redirect:/projects";
	}
}