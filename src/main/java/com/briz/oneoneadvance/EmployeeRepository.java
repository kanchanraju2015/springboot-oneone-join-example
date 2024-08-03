package com.briz.oneoneadvance;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee,Integer>
{
//List<Employee> findByOrderBySalary();// this is for order by salary ASC note this all records display

//List<Employee> findByOrderByName(); for order by name by default ascending all records 
	//List<Employee> findByOrderBySalaryAsc();// ascending order note this all records 
	List<Employee> findByOrderBySalaryDesc();// for descending order same all records 
	List<Employee> findTop5ByOrderByName();  // only 5 records with ascending order see for desc
	// check for like/contains/containing with regex expression dynamic query passing 
	List<Employee> findByNameContains(String name);
	@Query("select max(salary),min(salary),count(salary) from employee e")
	List<Object[]> statistics();
	//*******************************************************************//
	// join ,left join,inner join ,join fetch 
	//https://dzone.com/articles/how-to-decide-between-join-and-join-fetch#:~:text=
	//JOIN%20FETCH%20is%20specific%20to,objects%20using%20a%20single%20SELECT%20.
	@Query("from employee e left join fetch  e.address a where a.country=?1")
	//@Query("from employee where address.country=?1")// the perfect solution of business solution 
	// by default this is left join see the sql query generated into the log file 
	List<Employee> findByCountry(String country);// try and or not too here all are valid 
	//@Query("SELECT e FROM employee e JOIN e.address") // same as below by employee field
	//@Query("SELECT e FROM employee e left JOIN e.address") // same as above 
	@Query("FROM employee e JOIN e.address a where e.name like %?1%")// can also use left join same thing 
	// above is also valid use this in professional way
   //@Query("SELECT distinct e FROM employee e left JOIN e.address a where e.name like %?1%")for unique values 
	List<Employee> byjoin(String name);
	@Query("from employee e left join e.address a where a.city like %?1%")// by address field 
	List<Employee> bycityjoin(String city);
}
