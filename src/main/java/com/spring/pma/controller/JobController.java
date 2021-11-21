package com.spring.pma.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.spring.pma.dao.iJobRepository;
import com.spring.pma.entity.Employee;
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
	
//	@RequestMapping("/new")
	@GetMapping("/new")
	public String displayProjectForm(Model model) {
		model.addAttribute("job", new Job());
		return "jobs/new-job";
	}
	
	@GetMapping("/skill/{skill}")
	public String displayProjectsTests(Model model, @PathVariable("skill") String skill) {
		System.out.println(skill);
		List<Job> jobs = jobRepo.getJobsWithTheSkill(skill);
		model.addAttribute("jobList", jobs);
		return "jobs/list-jobs";
	}
	
//	@RequestMapping(value="/save", method = RequestMethod.POST)
	@PostMapping("/save")
	public String createProject(Job job, Model model) {
		//this should handle saving to the database
		jobRepo.save(job);
	
		
		return "redirect:/jobs";
	}
	

	@GetMapping("/edit/{id}")
	public String showUpdateForm(@PathVariable("id") long id, Model model) {
		Job jobs = jobRepo.findById(id)
	      .orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
	    
	    model.addAttribute("jobs", jobs);
	    return "jobs/update-job";
	}

	@PostMapping("/update/{id}")
	public String updateEmployee(@PathVariable("id") long id, @Valid Job jobs, 
	  BindingResult result, Model model) {
	    if (result.hasErrors()) {
	    	jobs.setJobId(id);
	        return "jobs/update-job";
	    }
		Job oldjob = jobRepo.findById(id)
			      .orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
		jobRepo.delete(oldjob);
		jobRepo.save(jobs);
		
//		Employee newEmp = new Employee();
//	    newEmp.setFirstName("firstName");
//	    newEmp.setLastName("lastName");
//	    newEmp.setEmail("email");
//	    empRepo.save(newEmp);
	    
	    model.addAttribute("jobs", jobRepo.findAll());
	    return "redirect:/jobs";
	}
	

	
//	@GetMapping("/delete/{id}")
//	public String deleteUser(@PathVariable("id") long id, Model model) {
//		Employee employee = empRepo.findById(id)
//	      .orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
//		empRepo.delete(employee);
//	    return "redirect:/employees";
//	}

}
