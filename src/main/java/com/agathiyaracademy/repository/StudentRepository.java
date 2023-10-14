package com.agathiyaracademy.repository;

import com.agathiyaracademy.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student,Long> {
}
