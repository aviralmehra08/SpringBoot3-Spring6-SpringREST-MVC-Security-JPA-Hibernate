package com.luv2code.crudDemo.dao;

import com.luv2code.crudDemo.entity.Course;
import com.luv2code.crudDemo.entity.Instructor;
import com.luv2code.crudDemo.entity.InstructorDetail;

import java.util.List;

public interface AppDAO {
    void save(Instructor theInstructor);
    Instructor findInstructorById(int theId);
    void deleteInstructorById(int theId);
    InstructorDetail findInstructorDetailById(int theId);
    void deleteInstructorDetailById(int theId);
    List<Course> findCourseByInstructorId(int theId);
    Instructor findInstructorByIdJoinFetch(int theId);
}
