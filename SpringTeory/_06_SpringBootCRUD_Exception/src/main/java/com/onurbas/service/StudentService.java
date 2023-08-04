package com.onurbas.service;

import com.onurbas.exception.ResourceNotFoundException;
import com.onurbas.model.Student;
import com.onurbas.repository.StudentRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
//@RequiredArgsConstructor //injection edilcek class final yapıldığında parametreli cons zorunlu tutar.
// Bu anotasyon
public class StudentService {

  private final StudentRepository studentRepository;

  @Autowired
  public StudentService(StudentRepository studentRepository) {
	this.studentRepository = studentRepository;
  }

  public List<Student> getAllStudents() {
	return studentRepository.findAll();
  }

  public ResponseEntity<Student> getOneStudent(Long id) throws ResourceNotFoundException {

	Student student = studentRepository.findById(id)
									   .orElseThrow(() -> new ResourceNotFoundException("Student not found ID : " + id));

	return ResponseEntity.ok().body(student);
  }

  public Student createStudent(Student student) {
	return studentRepository.save(student);
  }

  public String deleteOneStudent(Long id) {
	studentRepository.deleteById(id);
	return "Silindi";
  }

  public Student updateOneStudent(Student studentInfo) {
	return studentRepository.save(studentInfo);
  }
}
