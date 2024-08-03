package com.briz.oneoneadvance;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;

@Entity(name="employee")
public class Employee 
{
@Id
@GeneratedValue(strategy=GenerationType.IDENTITY)
int id;
String name;
int salary;
@OneToOne(cascade=CascadeType.ALL)
// if no joincolumn(Pk) and mapped by(FK)is used then both tables will have extra column of id.
@JoinColumn(name="address_id")// extra column will be created with Pk table but no extra col with address
Address address;
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public int getSalary() {
	return salary;
}
public void setSalary(int salary) {
	this.salary = salary;
}
public Address getAddress() {
	return address;
}
public void setAddress(Address address) {
	this.address = address;
}


}
