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

import com.ikould.decorate.entity.Material;
import com.ikould.decorate.entity.MaterialText;
import com.ikould.decorate.service.IMaterialService;
import com.ikould.decorate.service.IMaterialTextService;
import com.ikould.decorate.utils.PrintToClient;

@Controller
@RequestMapping(value = "/material")
public class MaterialController {

	@Autowired
	IMaterialService materialService;

	@Autowired
	IMaterialTextService materialTextService;

	/**
	 * 添加Material
	 */
	@RequestMapping(value = "/add_material")
	public void addMaterial(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		int id = materialService.getMaxId() + 1;
		int typeId = Integer.valueOf(request.getParameter("typeId"));
		int seriesId = Integer.valueOf(request.getParameter("seriesId"));
		String title = request.getParameter("title");
		String subtitle = request.getParameter("subtitle");
		String picPath = request.getParameter("picPath");
		String marketValue = request.getParameter("marketValue");
		String sellingValue = request.getParameter("sellingValue");
		String originalPrice = request.getParameter("originalPrice");
		String textStr = request.getParameter("text");
		long date = System.currentTimeMillis();

		// 插入MaterialText
		int textId = materialTextService.getMaxId() + 1;
		MaterialText text = new MaterialText(textId, id, textStr);
		materialTextService.insert(text);
		// 插入Material
		Material material = new Material(id, typeId, seriesId, title, subtitle,
				picPath, marketValue, sellingValue, originalPrice, textId, date);
		materialService.insert(material);
		PrintToClient.printSuccessMsgToClient(response);
	}

	/**
	 * 查找所有Material
	 */
	@RequestMapping(value = "/find_material")
	public void findMaterial(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		int seriesId = Integer.valueOf(request.getParameter("seriesId"));
		JSONObject data = new JSONObject();
		List<Material> materialList = materialService.findBySeriesId(seriesId);
		JSONArray materialJsonArray = new JSONArray();
		for (Material material : materialList) {
			JSONObject materialJson = new JSONObject();
			materialJson.put("id", material.getId());
			materialJson.put("typeId", material.getTypeId());
			materialJson.put("seriesId", material.getSeriesId());
			materialJson.put("title", material.getTitle());
			materialJson.put("subtitle", material.getSubtitle());
			materialJson.put("picPath", material.getPicPath());
			materialJson.put("marketValue", material.getMarketValue());
			materialJson.put("sellingValue", material.getSellingValue());
			materialJson.put("originalPrice", material.getOriginalPrice());
			materialJson.put("textId", material.getTextId());
			materialJson.put("date", material.getDate());
			materialJsonArray.add(materialJson);
		}
		data.put("materials", materialJsonArray);
		System.out.println("======================");
		System.out.println("======================");
		System.out.println("data = " + data);
		System.out.println("======================");
		System.out.println("======================");
		PrintToClient.printMsgToClient(response, data);
	}

	/**
	 * 查找所有Material
	 */
	@RequestMapping(value = "/find_material_by_id")
	public void findMaterialById(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		int id = Integer.valueOf(request.getParameter("id"));
		Material material = materialService.findById(id);
		// 获取文本信息
		MaterialText materialText = materialTextService.findAllByMaterialId(id);
		
		JSONObject materialJson = new JSONObject();
		materialJson.put("id", material.getId());
		materialJson.put("typeId", material.getTypeId());
		materialJson.put("seriesId", material.getSeriesId());
		materialJson.put("title", material.getTitle());
		materialJson.put("subtitle", material.getSubtitle());
		materialJson.put("picPath", material.getPicPath());
		materialJson.put("marketValue", material.getMarketValue());
		materialJson.put("sellingValue", material.getSellingValue());
		materialJson.put("originalPrice", material.getOriginalPrice());
		materialJson.put("text", materialText.getText());
		materialJson.put("date", material.getDate());
		
		System.out.println("======================");
		System.out.println("======================");
		System.out.println("data = " + materialJson);
		System.out.println("======================");
		System.out.println("======================");
		PrintToClient.printMsgToClient(response, materialJson);
	}

	/**
	 * 删除Material
	 */
	@RequestMapping(value = "/delete_material")
	public void deleteMaterial(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		int id = Integer.valueOf(request.getParameter("id"));
		Material material = materialService.findById(id);
		if (material != null) {
			// 删除MaterialText
			materialTextService.deleteById(material.getId());
			// 删除Material
			materialService.deleteById(material.getId());
			PrintToClient.printSuccessMsgToClient(response);
			return;
		}
		PrintToClient.printErrorMsgToClient(response);
	}

	/**
	 * 修改Material
	 */
	@RequestMapping(value = "/update_material")
	public void updateType(HttpServletRequest request,
			HttpServletResponse response) throws IOException {

		int id = Integer.valueOf(request.getParameter("id"));
		int typeId = Integer.valueOf(request.getParameter("typeId"));
		int seriesId = Integer.valueOf(request.getParameter("seriesId"));
		String title = request.getParameter("title");
		String subtitle = request.getParameter("subtitle");
		String picPath = request.getParameter("picPath");
		String marketValue = request.getParameter("marketValue");
		String sellingValue = request.getParameter("sellingValue");
		String originalPrice = request.getParameter("originalPrice");
		String textStr = request.getParameter("text");
		long date = System.currentTimeMillis();

		// 修改MaterialText
		MaterialText text = materialTextService.findAllByMaterialId(id);
		text.setText(textStr);
		materialTextService.update(text);
		// 修改Material
		Material material = new Material(id, typeId, seriesId, title, subtitle,
				picPath, marketValue, sellingValue, originalPrice,
				text.getId(), date);
		materialService.update(material);

		PrintToClient.printSuccessMsgToClient(response);
	}
}
