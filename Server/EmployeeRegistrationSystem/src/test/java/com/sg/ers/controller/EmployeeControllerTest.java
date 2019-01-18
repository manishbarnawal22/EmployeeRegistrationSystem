package com.sg.ers.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.sg.ers.bean.Employee;
import com.sg.ers.controller.EmployeeController;
import com.sg.ers.dao.EmployeeRepository;

import mockit.Expectations;
import mockit.Injectable;
import mockit.Tested;
import mockit.Verifications;

public class EmployeeControllerTest {
	
	@Tested
	private EmployeeController mEmployeeController;
	
	@Injectable
	private EmployeeRepository mEmployeeRepository;
	
	@Test
	public void testRegisterEmployee()
	{
		Employee lEmp = new Employee();
		lEmp.setDepartment("Rnd");
		lEmp.setDob(new Date());
		lEmp.setFirstname("Manish");
		lEmp.setLastname("Barnawal");
		lEmp.setGender("M");
		
		 new Expectations()
	        {
	            {
	            	mEmployeeRepository.save((Employee)any);
	                times = 1;
	            }
	        };
	        
	        ResponseEntity<String> lResponse = mEmployeeController.createUser(lEmp); 
	        
	        new Verifications()
	        {
	        	{
	        		mEmployeeRepository.save((Employee)any);
	                times = 1;
	        		Assert.assertEquals(lResponse.getStatusCode(), HttpStatus.CREATED);
	        	}
	        };
	}
	
	@Test
	public void testGetAllEmployees()
	{
		Employee lEmp1 = new Employee();
		lEmp1.setDepartment("Rnd");
		lEmp1.setDob(new Date());
		lEmp1.setFirstname("Manish");
		lEmp1.setLastname("Barnawal");
		lEmp1.setGender("M");
		
		Employee lEmp2 = new Employee();
		lEmp2.setDepartment("Operation");
		lEmp2.setDob(new Date());
		lEmp2.setFirstname("Mayur");
		lEmp2.setLastname("Barnawal");
		lEmp2.setGender("M");
		
		List<Employee> lListOfEmployees = new ArrayList<>();
		lListOfEmployees.add(lEmp1);
		lListOfEmployees.add(lEmp2);
		
		 new Expectations()
	        {
	            {
	            	mEmployeeRepository.findAll();
	            	result = lListOfEmployees;
	                times = 1;
	            }
	        };
	        
	        List<Employee> lListOfAllEmployees = mEmployeeController.getAllEmployee(); 
	        
	        new Verifications()
	        {
	        	{
	        		mEmployeeRepository.findAll();
	                times = 1;
	        		Assert.assertEquals(lListOfAllEmployees.size(), 2);
	        	}
	        };
	}
	
}
