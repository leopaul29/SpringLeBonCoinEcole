package com.leopaulmartin.spring.leboncoinecole.web.controllers.admin;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainAdminController {

	@Value("${spring.application.name}")
	String appName;
	@Value("${app.version}")
	String appVersion;

	@GetMapping({"/admin", "/admin/home"})
	public String adminHomePage(Model model) {
		model.addAttribute("appName", appName);
		model.addAttribute("appVersion", appVersion);
		return "admin/index";
	}
}
