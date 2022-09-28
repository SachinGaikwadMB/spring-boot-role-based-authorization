package com.mb.api.persistance.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "students")

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Student
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "email", unique = true)
	private String email;
	
	@Column(name = "mobile_number")
	private String mobileNumber;
	
	@Column(name = "adhar_number")
	private String adharNumber;
	
	@Column(name = "address", length = 250)
	private String address;
}
