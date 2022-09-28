package com.mb.api.web.controllers;

import static com.mb.api.constants.GenericConstants.STUDENTS;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.mb.api.business.services.StudentService;
import com.mb.api.persistance.entity.Student;

@RestController
@RequestMapping(STUDENTS)
@PreAuthorize("hasRole('USER')")
public class StudentController extends BaseController
{
	@Autowired
	private StudentService studentService;

	@GetMapping
	//@PreAuthorize("hasRole('USER')")
	public ResponseEntity<List<Student>> getAllStudents()
	{
		return new ResponseEntity<>(studentService.getAllStudents(), HttpStatus.OK);
	}

	@GetMapping("/{studentId}")
	//@PreAuthorize("hasRole('USER')")
	public ResponseEntity<Student> getStudentById(@PathVariable(name = "studentId", required = true) Integer studentId)
	{

		return new ResponseEntity<>(studentService.getStudentById(studentId), HttpStatus.OK);
	}
}
