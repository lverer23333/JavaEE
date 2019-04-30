package edu.bjtu.ee4j.repository.secondary;

import edu.bjtu.ee4j.domain.Person;

import edu.bjtu.ee4j.domain_second.Course;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseRepository extends CrudRepository<Course, Integer>{

	@Query("select c  from Course c where course_name=?1")
    public Course getByCourse_Name(String course_name);
 
    
}
