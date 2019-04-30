package edu.bjtu.ee4j.controller;

import org.springframework.cache.annotation.CachePut;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import edu.bjtu.ee4j.domain_second.Coach;

@Controller
public class HomeController {
	@CachePut(value = "index")
    @RequestMapping(value = { "", "/", "/home" })
    public String index(Coach coach, Model model) {
        model.addAttribute("activePage", "home");
        System.out.println("这是没有缓存的界面1");
        return "/index";
    }
   
}
