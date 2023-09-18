package com.luv2code.crudDemo.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.luv2code.crudDemo.entity.Course;
import com.luv2code.crudDemo.entity.Instructor;
import com.luv2code.crudDemo.entity.InstructorDetail;
import jakarta.persistence.EntityManager;

import java.util.ArrayList;

import org.hibernate.engine.spi.SessionDelegatorBaseImpl;
import org.hibernate.internal.SessionFactoryImpl;
import org.hibernate.internal.SessionImpl;
import org.junit.jupiter.api.Disabled;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {AppDAOImpl.class})
@ExtendWith(SpringExtension.class)
class AppDAOImplTest {
    @Autowired
    private AppDAOImpl appDAOImpl;

    @MockBean
    private EntityManager entityManager;

    @Test
    void testSave() {
        doNothing().when(entityManager).persist(Mockito.<Object>any());

        InstructorDetail instructorDetail = new InstructorDetail();
        instructorDetail.setHobby("Hobby");
        instructorDetail.setId(1);
        instructorDetail.setInstructor(new Instructor());
        instructorDetail.setYoutubeChannel("Youtube Channel");

        Instructor instructor = new Instructor();
        ArrayList<Course> courses = new ArrayList<>();
        instructor.setCourses(courses);
        instructor.setEmail("jane.doe@example.org");
        instructor.setFirstName("Jane");
        instructor.setId(1);
        instructor.setInstructorDetail(instructorDetail);
        instructor.setLastName("Doe");

        InstructorDetail instructorDetail2 = new InstructorDetail();
        instructorDetail2.setHobby("Hobby");
        instructorDetail2.setId(1);
        instructorDetail2.setInstructor(instructor);
        instructorDetail2.setYoutubeChannel("Youtube Channel");

        Instructor theInstructor = new Instructor();
        theInstructor.setCourses(new ArrayList<>());
        theInstructor.setEmail("jane.doe@example.org");
        theInstructor.setFirstName("Jane");
        theInstructor.setId(1);
        theInstructor.setInstructorDetail(instructorDetail2);
        theInstructor.setLastName("Doe");
        appDAOImpl.save(theInstructor);
        verify(entityManager).persist(Mockito.<Object>any());
        assertEquals(courses, theInstructor.getCourses());
        assertEquals("Doe", theInstructor.getLastName());
        assertSame(instructorDetail2, theInstructor.getInstructorDetail());
        assertEquals(1, theInstructor.getId());
        assertEquals("jane.doe@example.org", theInstructor.getEmail());
        assertEquals("Jane", theInstructor.getFirstName());
    }

    @Test
    void testFindInstructorById() {
        Instructor instructor = new Instructor();
        instructor.setCourses(new ArrayList<>());
        instructor.setEmail("jane.doe@example.org");
        instructor.setFirstName("Jane");
        instructor.setId(1);
        instructor.setInstructorDetail(new InstructorDetail());
        instructor.setLastName("Doe");

        InstructorDetail instructorDetail = new InstructorDetail();
        instructorDetail.setHobby("Hobby");
        instructorDetail.setId(1);
        instructorDetail.setInstructor(instructor);
        instructorDetail.setYoutubeChannel("Youtube Channel");

        Instructor instructor2 = new Instructor();
        instructor2.setCourses(new ArrayList<>());
        instructor2.setEmail("jane.doe@example.org");
        instructor2.setFirstName("Jane");
        instructor2.setId(1);
        instructor2.setInstructorDetail(instructorDetail);
        instructor2.setLastName("Doe");

        InstructorDetail instructorDetail2 = new InstructorDetail();
        instructorDetail2.setHobby("Hobby");
        instructorDetail2.setId(1);
        instructorDetail2.setInstructor(instructor2);
        instructorDetail2.setYoutubeChannel("Youtube Channel");

        Instructor instructor3 = new Instructor();
        instructor3.setCourses(new ArrayList<>());
        instructor3.setEmail("jane.doe@example.org");
        instructor3.setFirstName("Jane");
        instructor3.setId(1);
        instructor3.setInstructorDetail(instructorDetail2);
        instructor3.setLastName("Doe");
        when(entityManager.find(Mockito.<Class<Instructor>>any(), Mockito.<Object>any())).thenReturn(instructor3);
        assertSame(instructor3, appDAOImpl.findInstructorById(1));
        verify(entityManager).find(Mockito.<Class<Instructor>>any(), Mockito.<Object>any());
    }

    @Test
    void testDeleteInstructorById() {
        Instructor instructor = new Instructor();
        instructor.setCourses(new ArrayList<>());
        instructor.setEmail("jane.doe@example.org");
        instructor.setFirstName("Jane");
        instructor.setId(1);
        instructor.setInstructorDetail(new InstructorDetail());
        instructor.setLastName("Doe");

        InstructorDetail instructorDetail = new InstructorDetail();
        instructorDetail.setHobby("Hobby");
        instructorDetail.setId(1);
        instructorDetail.setInstructor(instructor);
        instructorDetail.setYoutubeChannel("Youtube Channel");

        Instructor instructor2 = new Instructor();
        instructor2.setCourses(new ArrayList<>());
        instructor2.setEmail("jane.doe@example.org");
        instructor2.setFirstName("Jane");
        instructor2.setId(1);
        instructor2.setInstructorDetail(instructorDetail);
        instructor2.setLastName("Doe");

        InstructorDetail instructorDetail2 = new InstructorDetail();
        instructorDetail2.setHobby("Hobby");
        instructorDetail2.setId(1);
        instructorDetail2.setInstructor(instructor2);
        instructorDetail2.setYoutubeChannel("Youtube Channel");

        Instructor instructor3 = new Instructor();
        instructor3.setCourses(new ArrayList<>());
        instructor3.setEmail("jane.doe@example.org");
        instructor3.setFirstName("Jane");
        instructor3.setId(1);
        instructor3.setInstructorDetail(instructorDetail2);
        instructor3.setLastName("Doe");
        when(entityManager.find(Mockito.<Class<Instructor>>any(), Mockito.<Object>any())).thenReturn(instructor3);
        doNothing().when(entityManager).remove(Mockito.<Object>any());
        appDAOImpl.deleteInstructorById(1);
        verify(entityManager).find(Mockito.<Class<Instructor>>any(), Mockito.<Object>any());
        verify(entityManager).remove(Mockito.<Object>any());
    }

    @Test
    void testFindInstructorDetailById() {
        InstructorDetail instructorDetail = new InstructorDetail();
        instructorDetail.setHobby("Hobby");
        instructorDetail.setId(1);
        instructorDetail.setInstructor(new Instructor());
        instructorDetail.setYoutubeChannel("Youtube Channel");

        Instructor instructor = new Instructor();
        instructor.setCourses(new ArrayList<>());
        instructor.setEmail("jane.doe@example.org");
        instructor.setFirstName("Jane");
        instructor.setId(1);
        instructor.setInstructorDetail(instructorDetail);
        instructor.setLastName("Doe");

        InstructorDetail instructorDetail2 = new InstructorDetail();
        instructorDetail2.setHobby("Hobby");
        instructorDetail2.setId(1);
        instructorDetail2.setInstructor(instructor);
        instructorDetail2.setYoutubeChannel("Youtube Channel");

        Instructor instructor2 = new Instructor();
        instructor2.setCourses(new ArrayList<>());
        instructor2.setEmail("jane.doe@example.org");
        instructor2.setFirstName("Jane");
        instructor2.setId(1);
        instructor2.setInstructorDetail(instructorDetail2);
        instructor2.setLastName("Doe");

        InstructorDetail instructorDetail3 = new InstructorDetail();
        instructorDetail3.setHobby("Hobby");
        instructorDetail3.setId(1);
        instructorDetail3.setInstructor(instructor2);
        instructorDetail3.setYoutubeChannel("Youtube Channel");
        when(entityManager.find(Mockito.<Class<InstructorDetail>>any(), Mockito.<Object>any()))
                .thenReturn(instructorDetail3);
        assertSame(instructorDetail3, appDAOImpl.findInstructorDetailById(1));
        verify(entityManager).find(Mockito.<Class<InstructorDetail>>any(), Mockito.<Object>any());
    }

    @Test
    void testDeleteInstructorDetailById() {
        InstructorDetail instructorDetail = new InstructorDetail();
        instructorDetail.setHobby("Hobby");
        instructorDetail.setId(1);
        instructorDetail.setInstructor(new Instructor());
        instructorDetail.setYoutubeChannel("Youtube Channel");

        Instructor instructor = new Instructor();
        instructor.setCourses(new ArrayList<>());
        instructor.setEmail("jane.doe@example.org");
        instructor.setFirstName("Jane");
        instructor.setId(1);
        instructor.setInstructorDetail(instructorDetail);
        instructor.setLastName("Doe");

        InstructorDetail instructorDetail2 = new InstructorDetail();
        instructorDetail2.setHobby("Hobby");
        instructorDetail2.setId(1);
        instructorDetail2.setInstructor(instructor);
        instructorDetail2.setYoutubeChannel("Youtube Channel");

        Instructor instructor2 = new Instructor();
        instructor2.setCourses(new ArrayList<>());
        instructor2.setEmail("jane.doe@example.org");
        instructor2.setFirstName("Jane");
        instructor2.setId(1);
        instructor2.setInstructorDetail(instructorDetail2);
        instructor2.setLastName("Doe");

        InstructorDetail instructorDetail3 = new InstructorDetail();
        instructorDetail3.setHobby("Hobby");
        instructorDetail3.setId(1);
        instructorDetail3.setInstructor(instructor2);
        instructorDetail3.setYoutubeChannel("Youtube Channel");
        doNothing().when(entityManager).remove(Mockito.<Object>any());
        when(entityManager.find(Mockito.<Class<InstructorDetail>>any(), Mockito.<Object>any()))
                .thenReturn(instructorDetail3);
        appDAOImpl.deleteInstructorDetailById(1);
        verify(entityManager).find(Mockito.<Class<InstructorDetail>>any(), Mockito.<Object>any());
        verify(entityManager).remove(Mockito.<Object>any());
    }

}

