package com.onurbas.controller;

import com.onurbas.exception.ResourceNotFoundException;
import com.onurbas.model.Student;
import com.onurbas.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


//    http://localhost:8080/api/v1
@RestController
@RequestMapping("/api/v1")
public class StudentController {


    private final StudentService studentService;
    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    //    http://localhost:8080/api/v1
    @GetMapping
    public String getSelamlama() {
        return "Hoşgeldiniz";
    }


    //    http://localhost:8080/api/v1/hello?studentFirstName=Burak&studentLastName=Delice
    @GetMapping("/hello")
    public String getHello(@RequestParam(value = "studentFirstName", defaultValue = "Dünya") String studentFirstName,
                           @RequestParam(value = "studentLastName") String studentLastName) {
        return "Merhaba " + studentFirstName + " " + studentLastName;
    }


    //    http://localhost:8080/api/v1/students
    @GetMapping("/students")
    public List<Student> getAllStudents() {
        return studentService.getAllStudents();
    }


    //   GET  http://localhost:8080/api/v1/students/1
    @GetMapping("/students/{id}")//path variable valuesu verilmese def olarak idle eşleştirirdi.matchup olmalı
    public ResponseEntity<Student> getOneStudent(@PathVariable(value = "id") Long id) throws ResourceNotFoundException {

        return studentService.getOneStudent(id);
    }


    // POST -  http://localhost:8080/api/v1/students
    @PostMapping("/students")
    public Student createStudent(@RequestBody Student student) {
        return studentService.createStudent(student);
    }


    // UPDATE
    //    http://localhost:8080/api/v1/students/1
 /*   @PutMapping("/students/{id}")
    public Student updateOneStudent(@PathVariable(value = "id") Long id,
                                    @RequestBody Student student) {
        Student studentInfo = studentService.getOneStudent(id);

        if (studentInfo != null) {
            studentInfo.setId(id);
            studentInfo.setFirstName(student.getFirstName());
            studentInfo.setLastName(student.getLastName());
            studentInfo.setEmail(student.getEmail());
            return studentService.updateOneStudent(studentInfo);
        }

        return null;
    }
*/

    // DELETE
    //    http://localhost:8080/api/v1/students/1
    @DeleteMapping("/students/{id}")
    public String deleteOneStudent(@PathVariable(value = "id") Long id) {
        return studentService.deleteOneStudent(id);
    }

}











