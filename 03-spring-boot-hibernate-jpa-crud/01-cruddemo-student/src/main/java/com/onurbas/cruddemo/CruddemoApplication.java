package com.onurbas.cruddemo;

import com.onurbas.cruddemo.dao.StudentDAO;
import com.onurbas.cruddemo.entity.Student;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;


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
		};
	}
public void createMultipleStudents(StudentDAO studentDao){
	Student student1 = Student.builder()
			.firstName("zaa")
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
