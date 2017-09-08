package com.ikould.decorate.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ikould.decorate.entity.Authority;
import com.ikould.decorate.service.IAuthorityService;
import com.ikould.decorate.utils.PrintToClient;

/**
 * 权限管理
 * 
 * @author ikould
 */
@Controller
@RequestMapping(value = "/authority")
public class AuthorityController {

	// 注冊上限
	private static final int SIZE_LIMIT = 30;

	@Autowired
	IAuthorityService authorityService;

	@RequestMapping(value = "/load")
	public void load(HttpServletRequest request, HttpServletResponse response)
			throws IOException {

		List<Authority> list = authorityService.findAll();
		if (list.size() > SIZE_LIMIT) {
			PrintToClient.printErrorMsgToClient(response);
			return;
		}
		String mac = request.getParameter("mac");
		String productName = request.getParameter("productName");
		String modelName = request.getParameter("modelName");
		String time = String.valueOf(System.currentTimeMillis());
		Authority authority = authorityService.findByMac(mac);
		if (authority == null) {
			// 插入信息
			Authority newAuthority = new Authority();
			newAuthority.setAuthority(1);
			newAuthority.setMac(mac);
			newAuthority.setModelName(modelName);
			newAuthority.setProductName(productName);
			newAuthority.setTime(time);
			authorityService.insert(newAuthority);
		} else {
			// 查看权限
			int authorityIndex = authority.getAuthority();
			if (authorityIndex == 0) {
				PrintToClient.printErrorMsgToClient(response);
				return;
			}
		}
		PrintToClient.printSuccessMsgToClient(response);
	}

	@RequestMapping(value = "/findAll")
	public void findAll(HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		List<Authority> authorityList = authorityService.findAll();
		JSONArray authorityJsonArray = new JSONArray();
		for (Authority authority : authorityList) {
			JSONObject authorityJson = new JSONObject();
			authorityJson.put("id", authority.getId());
			authorityJson.put("authority", authority.getAuthority());
			authorityJson.put("mac", authority.getMac());
			authorityJson.put("time", authority.getTime());
			authorityJson.put("productName", authority.getProductName());
			authorityJson.put("modelName", authority.getModelName());
			authorityJsonArray.add(authorityJson);
		}
		PrintToClient.printMsgToClient(response, authorityJsonArray);
	}

	@RequestMapping(value = "/delete")
	public void delete(HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		int id = Integer.valueOf(request.getParameter("id"));
		authorityService.deleteById(id);
		PrintToClient.printSuccessMsgToClient(response);
	}

	@RequestMapping(value = "/update")
	public void update(HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		String mac = request.getParameter("mac");
		int authorityIndex = Integer.valueOf(request.getParameter("authority"));
		String productName = request.getParameter("productName");
		String modelName = request.getParameter("modelName");
		Authority authority = authorityService.findByMac(mac);
		if (authority != null) {
			authority.setAuthority(authorityIndex);
			authority.setProductName(productName);
			authority.setModelName(modelName);
			authorityService.update(authority);
			PrintToClient.printSuccessMsgToClient(response);
		}
		PrintToClient.printErrorMsgToClient(response);
	}
}
