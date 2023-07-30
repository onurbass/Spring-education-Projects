package com.onurbas.cruddemo.dao;

import com.onurbas.cruddemo.entity.Student;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


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

    @Override
    public Student findById(Integer id) {

        return entityManager.find(Student.class,id);
    }

    @Override
    public List<Student> findAll() {
        TypedQuery<Student> typedQuery= entityManager.createQuery(" from Student order by firstName",Student.class);

        return typedQuery.getResultList();
    }
//transactionala ihtiyaç yok çünkü sadece sorgu yapıyoruz save gibi metotlarda lazım databasede değişiklik yaptığımız için
    @Override
    public List<Student> findByFirstName(String firstName) {
        TypedQuery<Student> typedQuery = entityManager.createQuery("from Student where firstName=:x",Student.class);
        typedQuery.setParameter("x",firstName);
        return typedQuery.getResultList();
    }

    @Transactional //ekledik çünkü databasede değişiklik yapılacak
    @Override
    public void update(Student student) {
        entityManager.merge(student);
    }

    @Transactional
    @Override
    public void delete(Integer id) {
        Student student=entityManager.find(Student.class,id);
        entityManager.remove(student);
    }

    @Override
    @Transactional
    public int deleteAll() {

        return  entityManager.createQuery("delete from Student ").executeUpdate();
    }
}
