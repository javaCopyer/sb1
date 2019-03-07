package com.mh.sb1.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class TemplateController {
	@RequestMapping("intojsp")
	public String intoJsp(Model model) {
		model.addAttribute("name", "进入jsp");
		return "jsp_tem";
	}
}
