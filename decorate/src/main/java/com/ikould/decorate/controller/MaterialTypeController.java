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
import com.ikould.decorate.entity.MaterialSeries;
import com.ikould.decorate.entity.MaterialType;
import com.ikould.decorate.service.IMaterialSeriesService;
import com.ikould.decorate.service.IMaterialService;
import com.ikould.decorate.service.IMaterialTextService;
import com.ikould.decorate.service.IMaterialTypeService;
import com.ikould.decorate.utils.PrintToClient;

@Controller
@RequestMapping(value = "/type")
public class MaterialTypeController {

	@Autowired
	IMaterialTypeService materialTypeService;

	@Autowired
	IMaterialSeriesService materialSeriesService;

	@Autowired
	IMaterialService materialService;

	@Autowired
	IMaterialTextService materialTextService;

	/**
	 * 添加MaterialType
	 */
	@RequestMapping(value = "/add_type")
	public void addType(HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		String name = request.getParameter("name");
		String msg = request.getParameter("msg");
		if (name != null && msg != null) {
			MaterialType type = new MaterialType();
			int id = materialTypeService.getMaxId() + 1;
			type.setId(id);
			type.setName(name);
			type.setMsg(msg);
			materialTypeService.insert(type);
			JSONObject typeJson = new JSONObject();
			typeJson.put("id", id);
			typeJson.put("name", name);
			typeJson.put("msg", msg);
			PrintToClient.printMsgToClient(response, typeJson);
			return;
		}
		PrintToClient.printErrorMsgToClient(response);
	}

	/**
	 * 查找所有MaterialType
	 */
	@RequestMapping(value = "/find_type")
	public void findType(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		System.out.println("/type/find_type");
		JSONObject data = new JSONObject();
		List<MaterialType> typeList = materialTypeService.findAll();
		JSONArray typeJsonArray = new JSONArray();
		for (MaterialType type : typeList) {
			JSONObject typeJson = new JSONObject();
			typeJson.put("id", type.getId());
			typeJson.put("name", type.getName());
			typeJson.put("msg", type.getMsg());
			JSONArray seriesJsonArray = new JSONArray();
			List<MaterialSeries> seriesList = materialSeriesService
					.findByTypeId(type.getId());
			for (MaterialSeries series : seriesList) {
				JSONObject seriesJson = new JSONObject();
				seriesJson.put("id", series.getId());
				seriesJson.put("name", series.getName());
				seriesJson.put("typeId", series.getTypeId());
				seriesJson.put("position", series.getPosition());
				seriesJson.put("msg", series.getMsg());
				seriesJsonArray.add(seriesJson);
			}
			typeJson.put("series", seriesJsonArray);
			typeJsonArray.add(typeJson);
		}
		data.put("types", typeJsonArray);
		System.out.println("======================");
		System.out.println("======================");
		System.out.println("data = " + data);
		System.out.println("======================");
		System.out.println("======================");
		PrintToClient.printMsgToClient(response, data);
	}

	/**
	 * 删除MaterialType
	 */
	@RequestMapping(value = "/delete_type")
	public void deleteType(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		int id = Integer.valueOf(request.getParameter("id"));
		MaterialType type = materialTypeService.findById(id);
		if (type != null) {
			List<MaterialSeries> seriesList = materialSeriesService
					.findByTypeId(id);
			for (MaterialSeries series : seriesList) {
				List<Material> materialList = materialService
						.findBySeriesId(series.getId());
				for (Material material : materialList) {
					// 删除MaterialText
					materialTextService.deleteById(material.getId());
				}
				// 删除Material
				materialService.deleteBySeriesId(series.getId());
			}
			// 删除MaterialSeries
			materialSeriesService.deleteByTypeId(id);
			// 删除MaterialType
			materialTypeService.deleteById(id);
			PrintToClient.printSuccessMsgToClient(response);
			return;
		}
		PrintToClient.printErrorMsgToClient(response);
	}

	/**
	 * 修改MaterialType
	 */
	@RequestMapping(value = "/update_type")
	public void updateType(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		int id = Integer.valueOf(request.getParameter("id"));
		String name = request.getParameter("name");
		String msg = request.getParameter("msg");
		MaterialType type = new MaterialType(id, name, msg);
		materialTypeService.update(type);
		PrintToClient.printSuccessMsgToClient(response);
	}
}
