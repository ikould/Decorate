package com.ikould.decorate.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ikould.decorate.entity.Material;
import com.ikould.decorate.entity.MaterialSeries;
import com.ikould.decorate.service.IMaterialSeriesService;
import com.ikould.decorate.service.IMaterialService;
import com.ikould.decorate.service.IMaterialTextService;
import com.ikould.decorate.utils.PrintToClient;

@Controller
@RequestMapping(value = "/series")
public class MaterialSeriesController {

	@Autowired
	IMaterialSeriesService materialSeriesService;

	@Autowired
	IMaterialService materialService;

	@Autowired
	IMaterialTextService materialTextService;

	/**
	 * 添加MaterialSeries
	 */
	@RequestMapping(value = "/add_series")
	public void addSeries(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		String name = request.getParameter("name");
		int typeId = Integer.valueOf(request.getParameter("typeId"));
		String msg = request.getParameter("msg");
		System.out.println("===============");
		System.out.println("typeId = " + typeId);
		System.out.println("===============");
		if (name != null && msg != null) {
			int seriesId = materialSeriesService.getMaxId() + 1;
			int position = materialSeriesService.getMaxPosition(typeId) + 1;
			MaterialSeries series = new MaterialSeries(seriesId, name, typeId,
					position, msg);
			materialSeriesService.insert(series);
			JSONObject seriesJson = new JSONObject();
			seriesJson.put("id", series.getId());
			seriesJson.put("name", series.getName());
			seriesJson.put("typeId", series.getTypeId());
			seriesJson.put("msg", series.getMsg());
			seriesJson.put("position", series.getPosition());
			PrintToClient.printMsgToClient(response, seriesJson);
			return;
		}
		PrintToClient.printErrorMsgToClient(response);
	}

	/**
	 * 查找所有MaterialSeries
	 */
	@RequestMapping(value = "/find_series")
	public void findSeries(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
	}

	/**
	 * 删除MaterialSeries
	 */
	@RequestMapping(value = "/delete_series")
	public void deleteSeries(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		int id = Integer.valueOf(request.getParameter("id"));
		MaterialSeries series = materialSeriesService.findById(id);
		if (series != null) {
			List<Material> materialList = materialService.findBySeriesId(series
					.getId());
			for (Material material : materialList) {
				// 删除MaterialText
				materialTextService.deleteById(material.getId());
			}
			// 删除Material
			materialService.deleteBySeriesId(series.getId());
			// 删除MaterialSeries
			materialSeriesService.deleteById(id);
			PrintToClient.printSuccessMsgToClient(response);
			return;
		}
		PrintToClient.printErrorMsgToClient(response);
	}

	/**
	 * 修改MaterialSeries
	 */
	@RequestMapping(value = "/update_series")
	public void updateSeries(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		int id = Integer.valueOf(request.getParameter("id"));
		int typeId = Integer.valueOf(request.getParameter("typeId"));
		int position = Integer.valueOf(request.getParameter("position"));
		String name = request.getParameter("name");
		String msg = request.getParameter("msg");
		MaterialSeries series = new MaterialSeries(id, name, typeId, position,
				msg);
		materialSeriesService.update(series);
		PrintToClient.printSuccessMsgToClient(response);
	}
}
