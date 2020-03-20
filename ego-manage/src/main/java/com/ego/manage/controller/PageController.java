package com.ego.manage.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PageController {
	@RequestMapping("/")
	public String welcome(){
//		System.out.println("aaa");
		return "index";
	}
	@RequestMapping("{page}")
	public String showPage(@PathVariable String page){
//		System.out.println("bbb");
		return page;
	}
}
