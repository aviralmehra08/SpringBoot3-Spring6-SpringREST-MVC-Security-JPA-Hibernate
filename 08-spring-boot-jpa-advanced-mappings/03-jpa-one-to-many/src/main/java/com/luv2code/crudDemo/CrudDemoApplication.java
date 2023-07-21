package com.luv2code.crudDemo;

import com.luv2code.crudDemo.dao.AppDAO;
import com.luv2code.crudDemo.entity.Course;
import com.luv2code.crudDemo.entity.Instructor;
import com.luv2code.crudDemo.entity.InstructorDetail;
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
	public CommandLineRunner commandLineRunner(AppDAO appDAO){
		return runner->{
//			createInstructor(appDAO);
//			findInstructor(appDAO);
//			deleteInstructor(appDAO);
//			findInstructorDetail(appDAO);
//			deleteInstructorDetail(appDAO);
//			createInstructorWithCourses(appDAO);
//			findInstructorWithCourses(appDAO);
//			findCoursesForInstructor(appDAO);
			findInstructorWithCoursesJoinFetch(appDAO);
		};
	}

	private void findInstructorWithCoursesJoinFetch(AppDAO appDAO) {
		int theId=1;
		//find the instructor
		System.out.println("Finding instructor id: "+theId);
		Instructor tempInstructor = appDAO.findInstructorByIdJoinFetch(theId);
		System.out.println("tempInstructor: "+tempInstructor);
		System.out.println("Associated Courses: "+tempInstructor.getCourses());
		System.out.println("DONE!!");
	}

	private void findCoursesForInstructor(AppDAO appDAO) {
		int theId=1;
		System.out.println("Finding Instructor id: "+theId);
		Instructor tempInstructor=appDAO.findInstructorById(theId);
		//find courses for Instructor
		System.out.println("Finding courses for instructor id: "+theId);
		List<Course> courses=appDAO.findCourseByInstructorId(theId);
		//associate the objects
		tempInstructor.setCourses(courses);
		System.out.println("the assocaited courses: "+tempInstructor.getCourses());
		System.out.println("DONE!!");
	}

	private void findInstructorWithCourses(AppDAO appDAO) {
		int theId=1;
		System.out.println("Finding Instructor id: "+theId);
		Instructor tempInstructor=appDAO.findInstructorById(theId);
		System.out.println("tempInstructor: "+tempInstructor);
		System.out.println("Associated Courses: "+tempInstructor.getCourses());
		System.out.println("DONE!!");
	}

	private void createInstructorWithCourses(AppDAO appDAO) {
		//Creating the instructor
		Instructor tempInstructor = new Instructor("Aman","Mehra","aman@gmail.com");
		//Creating the instructor detail
		InstructorDetail tempInstructorDetail = new InstructorDetail("http://www.luv2code.com/youtube","Video Game");
		//Associate the objects
		tempInstructor.setInstructorDetail(tempInstructorDetail);
		//create some courses
		Course tempCourse1 = new Course("Air Guitar");
		Course tempCourse2 = new Course("Pinball");
		//add courses to instructor
		tempInstructor.add(tempCourse1);
		tempInstructor.add(tempCourse2);
		//save the instructor
		System.out.println("Saving instructor: "+tempInstructor);
		System.out.println("The Courses: "+tempInstructor.getCourses());
		appDAO.save(tempInstructor);
		System.out.println("DONE!!");
	}

	private void deleteInstructorDetail(AppDAO appDAO) {
		int theId = 1;
		System.out.println("Deleting instructor id: "+theId);
		appDAO.deleteInstructorDetailById(theId);
		System.out.println("DONE!!");
	}

	private void findInstructorDetail(AppDAO appDAO) {
		int id=1;
		InstructorDetail tempInstructorDetail=appDAO.findInstructorDetailById(id);
		System.out.println("tempInstructorDetail: "+tempInstructorDetail);
		System.out.println("the associated instructor: "+tempInstructorDetail.getInstructor());
		System.out.println("DONE!!");
	}

	private void deleteInstructor(AppDAO appDAO) {
		int theId = 1;
		System.out.println("Deleting instructor id: "+theId);
		appDAO.deleteInstructorById(theId);
		System.out.println("DONE!!");
	}

	private void findInstructor(AppDAO appDAO) {
		int theID = 1;
		System.out.println("Finding instructor id: "+theID);
		Instructor tempInstructor = appDAO.findInstructorById(theID);
		System.out.println("tempInstructor: "+tempInstructor);
		System.out.println("the associate InstructorDetail only: "+tempInstructor.getInstructorDetail());
	}

	private void createInstructor(AppDAO appDAO) {
		//Creating the instructor
		Instructor tempInstructor = new Instructor("Aviral","Mehra","abc@gmail.com");
		//Creating the instructor detail
		InstructorDetail tempInstructorDetail = new InstructorDetail("http://www.luv2code.com/youtube","Luv 2 Code");
		//Associate the objects
		tempInstructor.setInstructorDetail(tempInstructorDetail);
		//Saving the instructor
		System.out.println("Saving instructor: "+tempInstructor);
		appDAO.save(tempInstructor);
		System.out.println("DONE!!");
	}
}
