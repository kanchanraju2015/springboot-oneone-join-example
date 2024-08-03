package com.briz.oneoneadvance;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;

@Entity(name="address")
public class Address 
{
@Id
@GeneratedValue(strategy=GenerationType.IDENTITY)
int id;
String city;
String country;
@OneToOne(mappedBy="address",cascade=CascadeType.ALL)
// always into the FK entity to stop extra column into the database check DB
// both join column and mapped by must be used into both for proper working 
@JsonBackReference("address")// to stop the repeating output use this annotation always into FK
Employee employee;
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public String getCity() {
	return city;
}
public void setCity(String city) {
	this.city = city;
}
public String getCountry() {
	return country;
}
public void setCountry(String country) {
	this.country = country;
}
public Employee getEmployee() {
	return employee;
}
public void setEmployee(Employee employee) {
	this.employee = employee;
}
}
