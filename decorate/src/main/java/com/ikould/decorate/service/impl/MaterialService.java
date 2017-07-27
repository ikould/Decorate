package com.ikould.decorate.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ikould.decorate.entity.Material;
import com.ikould.decorate.mapper.MaterialMapper;
import com.ikould.decorate.service.IMaterialService;

@Service("materialService")
public class MaterialService implements IMaterialService {

	@Autowired
	MaterialMapper materialMapper;

	@Override
	public void insert(Material material) {
		materialMapper.insert(material);
	}

	@Override
	public void deleteBySeriesId(int seriesId) {
		materialMapper.deleteBySeriesId(seriesId);
	}

	@Override
	public void deleteById(int id) {
		materialMapper.deleteByPrimaryKey(id);
	}

	@Override
	public void deleteByName(String name) {
		materialMapper.deleteByName(name);
	}

	@Override
	public List<Material> findBySeriesId(int seriesId) {
		return materialMapper.findAllBySeriesId(seriesId);
	}

	@Override
	public void update(Material material) {
		materialMapper.updateByPrimaryKeySelective(material);
	}

	@Override
	public int getMaxId() {
		int maxId = 0;
		List<Material> materialList = materialMapper.findAll();
		for (Material material : materialList) {
			if (material.getId() > maxId) {
				maxId = material.getId();
			}
		}
		return maxId;
	}

	@Override
	public Material findById(int id) {
		return materialMapper.selectByPrimaryKey(id);
	}
}
