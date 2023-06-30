package com.luv2code.crudDemo;

import com.luv2code.crudDemo.dao.StudentDAO;
import com.luv2code.crudDemo.entity.Student;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class CrudDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(CrudDemoApplication.class, args);
	}
	@Bean
	public CommandLineRunner commandLineRunner(StudentDAO studentDAO){
		return runner->{
//			createStudent(studentDAO);
//			createMultipleStudents(studentDAO);
//			readStudent(studentDAO);
//			queryForStudents(studentDAO);
//			queryForStudentsByLastName(studentDAO);
//			updateStudent(studentDAO);
			deleteStudent(studentDAO);
		};
	}

	private void deleteStudent(StudentDAO studentDAO) {
		int studentId = 7;
		System.out.println("Deleting Student id: "+studentId);
		studentDAO.delete(studentId);
	}

	private void updateStudent(StudentDAO studentDAO) {
		//retrieve student based on the id: primary key
		int studentId = 6;
		System.out.println("Getting student with id: "+studentId);
		Student myStudent = studentDAO.findById(studentId);
		//change first name to "Scooby"
		System.out.println("Updating Student....");
		myStudent.setFirstName("Scooby");
		//update the student
		studentDAO.update(myStudent);
		//display the updated student
		System.out.println("Updated student: "+myStudent);
	}

	private void queryForStudentsByLastName(StudentDAO studentDAO) {
		List<Student> theStudents = studentDAO.findByLastName("Jam");
		for(Student tempStudent: theStudents){
			System.out.println(tempStudent);
		}
	}

	private void queryForStudents(StudentDAO studentDAO) {
		//get a list of students
		List<Student> theStudents = studentDAO.findAll();
		//display list of students
		for(Student tempStudent: theStudents){
			System.out.println(tempStudent);
		}
	}

	private void readStudent(StudentDAO studentDAO) {
		//create a student object
		System.out.println("Creating new student object...");
		Student tempStudent = new Student("Jim","Jam","jim@gmail.com");
		//save the student
		System.out.println("Saving the student..");
		studentDAO.save(tempStudent);
		//display id of the saved student
		int theId = tempStudent.getId();
		System.out.println("Saved Student. Generated id: "+theId);
		//retrieve student id
		System.out.println("Retrieving Student with id: "+theId);
		Student myStudent = studentDAO.findById(theId);
		//display student
		System.out.println("Found Student " +myStudent);
	}

	private void createMultipleStudents(StudentDAO studentDAO) {
		//create multiple students
		System.out.println("Creating new Student objects...");
		Student tempStudent1 = new Student("Aman","Mehra","aman@gmail.com");
		Student tempStudent2 = new Student("Aki","Mehra","aki@gmail.com");
		Student tempStudent3 = new Student("Sam","Mehra","sam@gmail.com");
		//save the object
		System.out.println("Saving the students");
		studentDAO.save(tempStudent1);
		studentDAO.save(tempStudent2);
		studentDAO.save(tempStudent3);
	}

	private void createStudent(StudentDAO studentDAO) {
		//create student object
		System.out.println("Creating new student object...");
		Student tempStudent = new Student("Aviral","Mehra","abc@gmail.com");
		//save the student object
		System.out.println("Saving the student....");
		studentDAO.save(tempStudent);
		//display id of the saved student
		System.out.println("Saved Student. GeneratedId: "+tempStudent.getId());
	}
}
