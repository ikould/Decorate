package com.ikould.decorate.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ikould.decorate.entity.MaterialText;
import com.ikould.decorate.service.IMaterialTextService;
import com.ikould.decorate.utils.PrintToClient;

@Controller
@RequestMapping(value = "/text")
public class MaterialTextController {

	@Autowired
	IMaterialTextService materialTextService;

	/**
	 * 查找MaterialText
	 */
	@RequestMapping(value = "/find_text")
	public void findMaterial(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		int materialId = Integer.valueOf(request.getParameter("materialId"));
		MaterialText materialText = materialTextService
				.findAllByMaterialId(materialId);

		JSONObject textJson = new JSONObject();
		textJson.put("materialId", materialText.getMaterialId());
		textJson.put("text", materialText.getText());
		System.out.println("======================");
		System.out.println("materialText = " + materialText);
		System.out.println("======================");
		PrintToClient.printMsgToClient(response, textJson);

	}
}
