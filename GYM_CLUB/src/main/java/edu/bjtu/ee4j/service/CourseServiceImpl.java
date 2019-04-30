package edu.bjtu.ee4j.service;

import edu.bjtu.ee4j.domain_second.Course;
import edu.bjtu.ee4j.repository.secondary.CourseRepository;

//import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CourseServiceImpl implements CourseService {
	@Autowired
    private CourseRepository CourseRepository;
    
    @Autowired
    public void setCourseRepository(CourseRepository CourseRepository) {
        this.CourseRepository = CourseRepository;
    }
    
    @Override
    public Iterable<Course> getAllCourses() {
        return this.CourseRepository.findAll();
    }
    
    @Override
    public Course getCourseById(Integer id) {
        return this.CourseRepository.findById(id).orElse(null);
    }
    
    @Override
    public Course saveCourse(Course Course) {
        return (Course) this.CourseRepository.save(Course);
    }
    
    @Override
    public void deleteCourse(Integer id) {
        this.CourseRepository.deleteById(id);
    }

   

	@Override
	public Course getCourse(String Course_name) {
		// TODO Auto-generated method stub
		
		       return  this.CourseRepository.getByCourse_Name(Course_name);
		 
		   
	}

	


    
}
