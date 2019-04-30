package edu.bjtu.ee4j.controller;

import edu.bjtu.ee4j.domain_second.Coach;
import edu.bjtu.ee4j.domain_second.Course;
import edu.bjtu.ee4j.service.CoachService;
import edu.bjtu.ee4j.service.CourseService;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
@RequestMapping("/contacts")
public class CoachController<R> {
    private CoachService CoachService;

    @Autowired
    public void setCoachService(CoachService CoachService) {
        this.CoachService = CoachService;
    }
    
  
    @Cacheable(value = "coaches")
    @RequestMapping(value = "/query/{page}")
    //@ResponseBody
    public String Coach_query(@Valid Coach coach, BindingResult bindingResult,Model model,@PathVariable("page") int page) {
  
    	Collection<Coach> a = new ArrayList<Coach>();
    	
    	PageRequest pageRequest = PageRequest.of(page-1, 4);
    	
		Page<Coach> coachPage = this.CoachService.getAllCoaches(pageRequest);
		
		if(coach.getCoach_name()==null||coach.getCoach_name().equals("")){
    		
    		for (int i = 0; i < coachPage.getContent().size(); i++) {
    			 Coach c=coachPage.getContent().get(i);
    	        	  a.add(c);

    		}

    	}
		else{
		for (int i = 0; i < coachPage.getContent().size(); i++) {
			 Coach c=coachPage.getContent().get(i);
			 if(c.getCoach_name().indexOf(coach.getCoach_name())!=-1){
	        	  a.add(c);
	           }
		}
		}
        model.addAttribute("coaches",a);
        model.addAttribute("judge","yes");
    	
        return "/index";
       
    }
    @Cacheable(value = "coaches")
    @RequestMapping(value = "/quer/Coach", method = RequestMethod.POST)
    //@ResponseBody
    public String Coach_query1(@Valid Coach coach, BindingResult bindingResult,Model model) {
  
    	
    	Collection<Coach> a = new ArrayList<Coach>();
    	
    	PageRequest pageRequest = PageRequest.of(0, 4);
    	if(coach.getCoach_name()==null||coach.getCoach_name().equals("")){
    		Page<Coach> coachPage = this.CoachService.getAllCoaches(pageRequest);
    		for (int i = 0; i < coachPage.getContent().size(); i++) {
    			 Coach c=coachPage.getContent().get(i);
    	        	  a.add(c);
    		}
    	}
    	
    	else{
		Page<Coach> coachPage = this.CoachService.getAllCoaches(pageRequest);
		for (int i = 0; i < coachPage.getContent().size(); i++) {
			 Coach c=coachPage.getContent().get(i);
			 if(c.getCoach_name().indexOf(coach.getCoach_name())!=-1){
	        	  a.add(c);
	           }
		}
    	}
        model.addAttribute("coaches",a);
        model.addAttribute("judge","yes");
    	
        return "/index";
       
    }
    
  
    @RequestMapping(value = { "", "/coach_detail/{coach_name}"})
    public String index1(@PathVariable String coach_name,Coach coach,Course course, Model model) {
        model.addAttribute("activePage", "contacts");
        System.out.println(coach_name);
    	Coach co=this.CoachService.getCoach(coach_name);
        model.addAttribute("co", co);
        
        return "/index1";
    }
   
}
