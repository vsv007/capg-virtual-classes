package com.capg.studentrestapi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.capg.studentrestapi.exceptions.StudentNotFoundException;
import com.capg.studentrestapi.model.Student;
import com.capg.studentrestapi.repository.StudentJpaRepo;

@Service
public class StudentService {
	
	@Autowired(required = true)
	StudentJpaRepo Bhargav;

	public List<Student> getListOfStudents(){
      	return Bhargav.findAll();
	}
	public Student getStudent(int studentId) {
		if(!Bhargav.existsById(studentId)) {
			throw new StudentNotFoundException("student with id : ["+studentId+"] Not Found"); 
		}
		return Bhargav.getOne(studentId);
	}
	@Transactional
	public Student addStudent(Student student) {
		return Bhargav.save(student);
	}	
	@Transactional
	public boolean deleteStudent(int studentId) {
		Bhargav.deleteById(studentId);
		return !Bhargav.existsById(studentId);
	}
	@Transactional
	public Student updateStudent(Student newStudentData) {
		Student student=Bhargav.getOne(newStudentData.getStudentId());		
		student.setDob(newStudentData.getDob());
		student.setStudentName(newStudentData.getStudentName());
		Bhargav.save(student);
		return student;

}
}
