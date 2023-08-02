package com.onurbas.cruddemo;

import com.onurbas.cruddemo.dao.StudentDAO;
import com.onurbas.cruddemo.entity.Student;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;


@SpringBootApplication
public class CruddemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(CruddemoApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(StudentDAO studentDAO) {
		//student dao nesnesi aldı parametre olarak student imp ettiği için nesnesi sayılır
		//uygulama başlatığında metodun çalışmasını sağladı
		return runner -> {
			//createStudent(studentDAO);
			createMultipleStudents(studentDAO);
			//readStudent(studentDAO);
			//queryForStudents(studentDAO).forEach(System.out::println);
			//findByFirstName(studentDAO).forEach(System.out::println);
			//updateStudent(studentDAO);
			//deleteStudent(studentDAO);
			//deleteAllStudent(studentDAO);
		};
	}

	public int deleteAllStudent(StudentDAO studentDAO) {

		return studentDAO.deleteAll();

	}

	public void deleteStudent(StudentDAO studentDAO) {
		int id =2;

		studentDAO.delete(id);
	}

	public void updateStudent(StudentDAO studentDAO) {
		int studentId=1;
		Student student=studentDAO.findById(studentId);
		student.setFirstName("Duffy");
		studentDAO.update(student);

		System.out.println(student);
	}


	public List<Student> findByFirstName(StudentDAO studentDAO) {
		return studentDAO.findByFirstName("zaa");
	}

	public List<Student> queryForStudents(StudentDAO studentDAO) {
		return studentDAO.findAll();
	}

	public void readStudent(StudentDAO studentDAO) {
		//create a student object
		Student student = Student.builder().firstName("onur").lastName("bas").email("onurbass").build();
		//save the student
		studentDAO.save(student);
		//display id of the save student
		System.out.println("Student id : "+student.getId());
		//retrieve student based on the id
		Student student1=studentDAO.findById(student.getId());
		//display student
		System.out.println(student1);
	}

	public void createMultipleStudents(StudentDAO studentDao){
	Student student1 = Student.builder()
			.firstName("Onur")
			.lastName("Bas")
			.email("zaa@gmail.com")
			.build();
	Student student2 = Student.builder()
			.firstName("Sila")
			.lastName("Bas")
			.email("silas@gmail.com")
			.build();
	Student student3 = Student.builder()
			.firstName("sevcan")
			.lastName("bas")
			.email("serk@gmail.com")
			.build();
studentDao.save(student1);
studentDao.save(student2);
studentDao.save(student3);

}
	private void createStudent(StudentDAO studentDAO) {
		//create student
		Student student = Student.builder()
				.firstName("Onur")
				.lastName("Bas")
				.email("onurbass@gmail.com")
				.build();

		//save the student
		studentDAO.save(student);
		//display id of the saved studen
		System.out.println("Saved student id : "+student.getId());
	}

}
