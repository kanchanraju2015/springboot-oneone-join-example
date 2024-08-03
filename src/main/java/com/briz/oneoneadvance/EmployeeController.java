package com.briz.oneoneadvance;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// https://thorben-janssen.com/hibernate-tips-difference-join-left-join-fetch-join/
@RestController
public class EmployeeController 
{
@Autowired
EmployeeRepository erepo;
@Autowired
AddressRepository arepo;
@RequestMapping("/test")
public String test()
{
	return "this is one one advance test";
}
@RequestMapping("/save")
public String save()
{
	Employee e=new Employee();
	Address a=new Address();
	a.setCity("patna");
	a.setCountry("india");
	e.setName("dhanush");
	e.setSalary(3000);
	e.setAddress(a);// both must be set
	a.setEmployee(e);// both must be set note this is bidirectional check the entity
	erepo.save(e);
	return "data saved into database";	
}
/*
   THIS IS BIDIRECTIONAL BOTH REPO CAN BE USED IN BOTH DIRECTIONS
@RequestMapping("/all")
public List<Employee> alldata()
{
	return erepo.findAll();
}
*/
@RequestMapping("/all")// ALL DATA I.E BOTH TABLE DATA WILL BE DISPLAYED HERE 
public List<Address> alldata()
{
	return arepo.findAll();
}

@RequestMapping("/{id}")
public Optional<Employee> byid(@PathVariable int id)
{
	return erepo.findById(id);
}
@RequestMapping("/orderby")
public List<Employee> ordered()
{
	return erepo.findByOrderBySalaryDesc();
	}
@RequestMapping("/top")
public List<Employee> top()
{
	return erepo.findTop5ByOrderByName();
	}
@RequestMapping("/like/{name:[a-z]{3}}")// must be alphabet and at least 3 character note this 
//@RequestMapping("/like/{name:[a-z]+}") // any number of character in search pattern 
// @RequestMapping("/like/{uid:[0-9]{12}}") only 12 digits all numerics  needed 
public List<Employee> like(@PathVariable String name)
{
	return erepo.findByNameContains(name);// findbynamecontaining/findbynamelike etc same 
	//  findByNameContainingIgnoreCase(name) case in-sensitivity
	// findbynamenotlike/notconaining/notcontains all are valid example 
	// check for startswith,endswith all having different usage 
}
@RequestMapping("/statistics")
public String  st()
{
	List<Object[]> data=erepo.statistics();// calling the repository method 
	for(Object[] x:data) // important concept here note this line 
		System.out.println(x[0]+"  "+x[1]+"   "+x[2]);
	    return "data in console log";
	}
@RequestMapping("/join/{name}")
public List<Employee> joined(@PathVariable String name)
{
	return erepo.byjoin(name);
}
@RequestMapping("/join/city/{city}")
public List<Employee> bycityjoin(@PathVariable String city)
{
	return erepo.bycityjoin(city);
}
@RequestMapping("/by/{country}")
public List<Employee> bycity(@PathVariable String country)
{
	return erepo.findByCountry(country);
}
}
