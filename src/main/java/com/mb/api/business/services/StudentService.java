package com.mb.api.business.services;

import java.util.List;
import com.mb.api.persistance.entity.Student;

public interface StudentService
{
	List<Student> getAllStudents();
	
	Student getStudentById(Integer id);
}
