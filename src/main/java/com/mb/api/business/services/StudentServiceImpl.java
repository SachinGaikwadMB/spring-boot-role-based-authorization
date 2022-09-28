package com.mb.api.business.services;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.mb.api.business.exceptions.CustomException;
import com.mb.api.persistance.entity.Student;
import com.mb.api.persistance.repository.StudentRepository;

@Service
public class StudentServiceImpl implements StudentService
{

	@Autowired
	private StudentRepository studentRepository;

	@Override
	public List<Student> getAllStudents()
	{
		try
		{
			return studentRepository.findAll();
		}
		catch (Exception e)
		{
			throw new CustomException("Internal Server Error !!!");
		}

	}

	@Override
	public Student getStudentById(Integer id)
	{
		Optional<Student> optStudent = studentRepository.findById(id);

		if (!optStudent.isPresent())
		{
			throw new CustomException("User with ID :: " + id + " not present !!");
		}
		return optStudent.get();
	}

}
