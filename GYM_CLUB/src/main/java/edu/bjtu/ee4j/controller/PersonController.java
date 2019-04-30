package edu.bjtu.ee4j.controller;

import edu.bjtu.ee4j.domain.Person;
import edu.bjtu.ee4j.domain_second.Coach;
import edu.bjtu.ee4j.service.PersonService;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/contacts")
public class PersonController<R> {
    private PersonService PersonService;

    @Autowired
    public void setPersonService(PersonService PersonService) {
        this.PersonService = PersonService;
    }
    
    @CachePut(value = "index")
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index(Coach coach, Model model) {
        model.addAttribute("activePage", "contacts");
       // model.addAttribute("contacts", this.PersonService.getAllPersons());
        System.out.println("这是没有缓存的界面2");
        return "/index";
    }
    
    @CachePut(value = "about")
    @RequestMapping(value = { "", "/about" })
    public String index1(Model model) {
        model.addAttribute("activePage", "contacts");
        //model.addAttribute("Persons", this.PersonService.getAllPersons());
        return "/about";
    }
    
    @CachePut(value = "map")
    @RequestMapping(value = { "", "/map" })
    public String index0(Model model) {
        model.addAttribute("activePage", "contacts");
        //model.addAttribute("Persons", this.PersonService.getAllPersons());
        return "/map";
    }

    @CachePut(value = "portfolio")
    @RequestMapping(value = { "", "/portfolio" })
    public String index2(Model model) {
        model.addAttribute("activePage", "contacts");
       // model.addAttribute("Persons", this.PersonService.getAllPersons());
        return "/portfolio";
    }
    
    @CachePut(value = "shortcodes")
    @RequestMapping(value = { "", "/shortcodes" })
    public String index3(Model model) {
        model.addAttribute("activePage", "contacts");
      //  model.addAttribute("Persons", this.PersonService.getAllPersons());
        return "/shortcodes";
    }
    
    @CachePut(value = "single")
    @RequestMapping(value = { "", "/single" })
    public String index4(Model model) {
        model.addAttribute("activePage", "contacts");
        //model.addAttribute("Persons", this.PersonService.getAllPersons());
        return "/single";
    }
    
    @CachePut(value = "person")
    @RequestMapping(value = "/person", method = RequestMethod.GET)
    public String index5(Person person, Model model) {
        model.addAttribute("activePage", "contacts");
       // model.addAttribute("contacts", this.PersonService.getAllPersons());
        return "/contact";
    }
    
    @CachePut(value = "services")
    @RequestMapping(value = { "", "/services" })
    public String index6(Model model) {
        model.addAttribute("activePage", "contacts");
        //model.addAttribute("Persons", this.PersonService.getAllPersons());
        return "/services";
    }

    @RequestMapping(value = "/login1", method = RequestMethod.GET)
    public String index7(Person person,Model model) {
        model.addAttribute("activePage", "contacts1");
        model.addAttribute("hhh", "Register");
        return "/login";
    }
 
    
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    //@ResponseBody
    public String Register(@Valid Person person, BindingResult bindingResult, Model model,HttpServletResponse response,HttpServletRequest request) {
    	boolean judge=true;
        if (bindingResult.hasErrors()) {
        	 judge=false;
            return "/contact";
           
        }
      
      if(this.PersonService.getUser(person.getEmail())!=null){
    	  model.addAttribute("err", "The email has been registered!");
    	  judge=false;
    	  return "/contact";
      }
      else if(this.PersonService.getUser1(person.getPhone_no())!=null){
    	  model.addAttribute("err1", "The phone has been registered!");
    	  judge=false;
          return "/contact";
      }
        
        this.PersonService.savePerson(person);
        try {
			this.addCookie(person.getPhone_no(),"", response, request);
			this.addCookie(person.getEmail().replace("@",""),"", response, request);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
         model.addAttribute("hhh", "Login");
         return "/login";
    }
    
    
    @RequestMapping(value = "/login2",method = RequestMethod.POST)
    public String viewPerson(@Valid Person person, BindingResult bindingResult, Model model,HttpServletResponse response,HttpServletRequest request) {
    	HttpSession session=request.getSession();
    	boolean judge=true;
    	if(session.getValue("name")==null){
    		if(person.getEmail()!=null){
    	
    		 
    			  String mailRegex,mailName,mailDomain;
    		        mailName="^[0-9a-z]+\\w*";       //^表明一行以什么开头；^[0-9a-z]表明要以数字或小写字母开头；\\w*表明匹配任意个大写小写字母或数字或下划线
    		        mailDomain="([0-9a-z]+\\.)+[0-9a-z]+$";       //***.***.***格式的域名，其中*为小写字母或数字;第一个括号代表有至少一个***.匹配单元，而[0-9a-z]$表明以小写字母或数字结尾
    		        mailRegex=mailName+"@"+mailDomain;          //邮箱正则表达式      ^[0-9a-z]+\w*@([0-9a-z]+\.)+[0-9a-z]+$
    		        Pattern pattern=Pattern.compile(mailRegex);
    		         Matcher matcher=pattern.matcher(person.getEmail());
    		         if(!matcher.matches()){
    			  model.addAttribute("err1", "Wrong Email.");
    			  judge=false;
    			  return "/login";
    			 
    		         }
    			
    	      
    	  if(this.PersonService.getUser(person.getEmail())!=null&&this.PersonService.getUser(person.getEmail()).equals(person.getPassword())){
    		  session.putValue("name", person.getEmail());
    		String str[]=request.getParameterValues("pass");
    		if(str!=null && str[0].equals("on")){
    			System.out.println("保存了密码:");
    			try {
    				this.addCookie(person.getEmail().replace("@",""),person.getPassword(), response, request);
				} catch (UnsupportedEncodingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
    		}
    		else{
    			try {
    				this.addCookie(person.getEmail().replace("@",""),"", response, request);
				} catch (UnsupportedEncodingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
    		}
    	
    			 
    		
       	      return "/services";
         }
    	  else{
    		  model.addAttribute("err", "Wrong Password!");
    		  judge=false;
              return "/login";
    	  }
    		}
    	
    	
    		 else{
        		 session.putValue("name", person.getPhone_no());
        			
        			
           	  if (person.getPhone_no().length()!=11) {
       			  model.addAttribute("err1", "Mobile no. must be 11 digits.");
       			 judge=false;
       			  return "/login";
       	        }
           	  else if(this.PersonService.getUser1(person.getPhone_no()).equals(person.getPassword())){
            
        	      
            	  if(this.PersonService.getUser(person.getPhone_no())!=null&&this.PersonService.getUser(person.getPhone_no()).equals(person.getPassword())){
            		  String str[]=request.getParameterValues("pass");
            		  if(str!=null && str[0].equals("on")){
                			System.out.println("保存了密码:");
                			try {
            					this.addCookie(person.getPhone_no(), person.getPassword(), response, request);
            				} catch (UnsupportedEncodingException e) {
            					// TODO Auto-generated catch block
            					e.printStackTrace();
            				}
                		}
                		else{
                			try {
            					this.addCookie(person.getPhone_no(),"", response, request);
            				} catch (UnsupportedEncodingException e) {
            					// TODO Auto-generated catch block
            					e.printStackTrace();
            				}
                		}
               	  return "/services";
                 }
            	  else{
            		  model.addAttribute("err", "Wrong Password!");
            		  judge=false;
                      return "/login";
            	  }
        	 }
        	 }
    	 }
 
    		
    	 else{
    	
    	 return "/services";
    	 }
    
    	 return "/services";
    }
    
 
    @RequestMapping(value = "/edit/{id}")
    public String editPerson(@PathVariable Integer id, Model model) {
        model.addAttribute("Person", this.PersonService.getPersonById(id));
        model.addAttribute("activePage", "contacts");
        return "contacts/edit";
    }
    
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String updatePerson(Person Person) {
        System.out.println("Person ID: " + Person.getId());
        this.PersonService.savePerson(Person);
        return "redirect:/contacts/view/" + Person.getId();
    }
    
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String deletePerson(@PathVariable Integer id) {
        this.PersonService.deletePerson(id);
        return "redirect:/contacts";
    } 
    public  void addCookie(String userName,String password,HttpServletResponse response, HttpServletRequest request) throws UnsupportedEncodingException{
        //创建cookie
        Cookie nameCookie = new Cookie(userName, password);
        nameCookie.setPath(request.getContextPath()+"/");//设置cookie路径
        //设置cookie保存的时间 单位：秒
        nameCookie.setMaxAge(7*24*60*60);
        //将cookie添加到响应
        response.addCookie(nameCookie);            
    }
    @ResponseBody
    @RequestMapping(value="/getCookie",method=RequestMethod.POST)
    public Map<String, String> initCookie(String email, HttpServletRequest request){
        Cookie[] cookie = request.getCookies();
        Map<String, String> map = new HashMap<>();
        for(Cookie c : cookie) {
            if(c.getName().equals(email.replace("@", ""))) {
                String password = c.getValue();
                map.put("email",email);
                map.put("password", password);
                return map;
            }
        }
        return null;
    }
}
