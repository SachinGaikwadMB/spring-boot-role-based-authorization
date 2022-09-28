package com.mb.api.persistance.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.mb.api.persistance.entity.Student;

public interface StudentRepository extends JpaRepository<Student, Integer>
{
	
}
