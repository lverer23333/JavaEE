package edu.bjtu.ee4j.service;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;

import edu.bjtu.ee4j.domain_second.Coach;
import edu.bjtu.ee4j.domain_second.Course;

public interface CourseService {
    Iterable<Course> getAllCourses();
    Course getCourseById(Integer id);
    Course saveCourse(Course Course);
	void deleteCourse(Integer id);
	public Course getCourse(String Course_name);


  
	
}
