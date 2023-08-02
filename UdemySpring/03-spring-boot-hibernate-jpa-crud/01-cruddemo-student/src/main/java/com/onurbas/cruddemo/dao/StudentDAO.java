package com.onurbas.cruddemo.dao;

import com.onurbas.cruddemo.entity.Student;

import java.util.List;

public interface StudentDAO {
    void save(Student student);

    Student findById(Integer id);
    List<Student> findAll();

   List<Student>  findByFirstName(String firstName);
   void update(Student student);
   void delete(Integer id);
   int deleteAll();

}
