package com.onurbas.rest;

import com.onurbas.entity.Student;
import jakarta.annotation.PostConstruct;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")

public class StudentRestController {
  private List<Student> studentList;

@PostConstruct//listeyi tek sefer yüklemeye yarar
public void loadDate(){
  studentList=new ArrayList<>();

  studentList.add(new Student("onur","bas"));
  studentList.add(new Student("sila","bas"));
  studentList.add(new Student("sevcan","bas"));
  studentList.add(new Student("mary","rossi"));
  studentList.add(new Student("mario","smitth"));


}
  //define endpoint for "/students"-return list of students


  @GetMapping("/students")//student listesi dönme
  public List<Student> getStudents(){

	return studentList;
  }

  @GetMapping("/students/{studentId}")//tek bi student dönme
  public Student getStudent(@PathVariable int studentId){

  return studentList.get(studentId);
  }


}
