package com.onurbas.cruddemo.dao;

import com.onurbas.cruddemo.entity.Student;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


@Repository //DAO olduğunu belirttik
public class StudentRepository implements StudentDAO {
    private EntityManager entityManager;

    @Autowired
    public StudentRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }


    @Override
    @Transactional // transaction işlemlerini yaptı tek satırda
    public void save(Student student) {
        entityManager.persist(student);

    }
}
