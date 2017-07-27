package com.ikould.decorate.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.ikould.decorate.service.IAdminService;

@Controller
public class ManagerController {

	@Autowired
	IAdminService adminService;

	/*@RequestMapping(value = "/manager")
	public String findManager(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		String name = request.getParameter("name");
		String password = request.getParameter("password");
		Admin admin = adminService.getAdminByName(name);
		if (admin != null && admin.getPassword().equals(password))
			return "manager";
		else
			return "admin";
	}*/
}
