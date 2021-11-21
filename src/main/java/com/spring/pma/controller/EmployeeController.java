package com.spring.pma.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.spring.pma.dao.iEmployeeRepository;
import com.spring.pma.entity.Employee;

@Controller
@RequestMapping("/employees")
public class EmployeeController {
	
	@Autowired
	iEmployeeRepository empRepo;
	
	@GetMapping
	public String displayEmployees(Model model) {
		List<Employee> employees = empRepo.findAll();
		model.addAttribute("employeeList", employees);
		
		return "employees/list-employees";
	}

	@GetMapping("/new")
	public String displayEmployeeForm(Model model) {
		model.addAttribute("employee", new Employee());
		return "employees/new-employee";
	}
	
	@PostMapping("/save")
	public String createEmployee(Employee employee, Model model) {
		empRepo.save(employee);
		return "redirect:/employees";
	}

	@GetMapping("/edit/{id}")
	public String showUpdateForm(@PathVariable("id") long id, Model model) {
		Employee employee = empRepo.findById(id)
	      .orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
	    
	    model.addAttribute("employee", employee);
	    return "employees/update-employee";
	}

	@PostMapping("/update/{id}")
	public String updateEmployee(@PathVariable("id") long id, @Valid Employee employee, 
	  BindingResult result, Model model) {
	    if (result.hasErrors()) {
	    	employee.setEmployeeId(id);
	        return "employees/update-employee";
	    }
		Employee oldEmp = empRepo.findById(id)
			      .orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
		empRepo.delete(oldEmp);
		empRepo.save(employee);
		
//		Employee newEmp = new Employee();
//	    newEmp.setFirstName("firstName");
//	    newEmp.setLastName("lastName");
//	    newEmp.setEmail("email");
//	    empRepo.save(newEmp);
	    
	    model.addAttribute("employees", empRepo.findAll());
	    return "redirect:/employees";
	}
	

	
	@GetMapping("/delete/{id}")
	public String deleteUser(@PathVariable("id") long id, Model model) {
		Employee employee = empRepo.findById(id)
	      .orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
		empRepo.delete(employee);
	    return "redirect:/employees";
	}
	
	


    
	
//	@GetMapping("/update/{id}")
//	public String showUpdateForm(@PathVariable("id") String id, Model model) {
//		Long longId = Long.parseLong(id);
//	    Employee em = empRepo.findById (longId)
//	      .orElseThrow(() -> new IllegalArgumentException("Invalid employee Id:" + id));
//	    model.addAttribute(em);
//	    return "employees/update-employee";
//	}
//	
//	@PostMapping("/update")
//	public String updateEmployee(Employee employee, Model model) {
//		empRepo.updateEmployee(employee.getEmployeeId(),employee.getFirstName(),employee.getFirstName(),employee.getFirstName());
//		return "redirect:/employees";
//	}
	
//	@PutMapping("/employees/update")
//	public ResponseEntity<Employee> updateEmployee(@PathVariable(value = "id") Long employee_id,
//	          @RequestBody Employee employeeDetails){
//	    Employee employee = empRepo.findById(employee_id)
//	    .orElseThrow();
//	   
//	    employee.setEmail(employeeDetails.getEmail());
//	    employee.setLastName(employeeDetails.getLastName());
//	    employee.setFirstName(employeeDetails.getFirstName());
//	    final Employee updatedEmployee = empRepo.save(employee);
//	    return ResponseEntity.ok(updatedEmployee);
//	   
//	}
}