package edu.bjtu.ee4j.controller;

import edu.bjtu.ee4j.domain_second.Coach;
import edu.bjtu.ee4j.domain_second.Course;
import edu.bjtu.ee4j.service.CourseService;
import edu.bjtu.ee4j.service.CourseService;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
@RequestMapping("/contacts")
public class CourseController<R> {
    private CourseService CourseService;

    @Autowired
    public void setCourseService(CourseService CourseService) {
        this.CourseService = CourseService;
    }
    
  
    @Cacheable(value = "courses")
    @RequestMapping(value = "/query1/Course", method = RequestMethod.POST)
    //@ResponseBody
    public String course_query(@Valid Course Course,BindingResult bindingResult,Model model) {

    	Collection<Course> courses = new ArrayList<Course>();

         Iterable<Course> iterable=this.CourseService.getAllCourses();
         Iterator<Course> iterator1=iterable.iterator();
         while (iterator1.hasNext()) {
        	
           Course c= iterator1.next();
           if(c.getCourse_name().indexOf(Course.getCourse_name())!=-1){
        	  courses.add(c);
           }
         } 
        model.addAttribute("course",courses);
    	
    	
        return "/index";
       
    }
  
    @RequestMapping(value = { "", "/course_detail/{course_name}"})
    public String index1(@PathVariable String course_name,Coach coach,Course course, Model model) {
        model.addAttribute("activePage", "contacts");
        
    	Course cour=this.CourseService.getCourse(course_name);
        model.addAttribute("cour", cour);
        
        return "/index2";
    }
  
   
}
