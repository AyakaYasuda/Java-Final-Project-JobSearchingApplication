package com.spring.pma.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.spring.pma.dao.iJobRepository;
import com.spring.pma.entity.Job;
import com.spring.pma.entity.Project;

@Controller
@RequestMapping("/jobs")
public class JobController {
	
	@Autowired
	iJobRepository jobRepo;
	
	@GetMapping
	public String displayJobs(Model model) {
		List<Job> jobs = jobRepo.findAll();
		model.addAttribute("jobList", jobs);
		
		return "jobs/list-jobs";
	}
	
	@GetMapping("/skill/{skill}")
	public String displayProjectsTests(Model model, @PathVariable("skill") String skill) {
		System.out.println(skill);
		List<Job> jobs = jobRepo.getJobsWithTheSkill(skill);
		model.addAttribute("jobList", jobs);
		return "jobs/list-jobs";
	}

}
