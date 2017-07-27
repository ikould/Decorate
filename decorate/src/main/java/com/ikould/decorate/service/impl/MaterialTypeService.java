package com.ikould.decorate.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ikould.decorate.entity.MaterialType;
import com.ikould.decorate.mapper.MaterialTypeMapper;
import com.ikould.decorate.service.IMaterialTypeService;

@Service("materialTypeService")
public class MaterialTypeService implements IMaterialTypeService {

	@Autowired
	MaterialTypeMapper materialTypeMapper;

	@Override
	public void insert(MaterialType type) {
		materialTypeMapper.insert(type);
	}

	@Override
	public void deleteById(int id) {
		materialTypeMapper.deleteByPrimaryKey(id);
	}

	@Override
	public void deleteByName(String typeName) {
		materialTypeMapper.deleteByName(typeName);
	}

	@Override
	public List<MaterialType> findAll() {
		return materialTypeMapper.findAll();
	}

	@Override
	public void update(MaterialType type) {
		materialTypeMapper.updateByPrimaryKey(type);
	}

	@Override
	public int getMaxId() {
		int maxId = 0;
		List<MaterialType> typeList = materialTypeMapper.findAll();
		for (MaterialType type : typeList) {
			if (type.getId() > maxId) {
				maxId = type.getId();
			}
		}
		return maxId;
	}

	@Override
	public MaterialType findById(int id) {
		return materialTypeMapper.selectByPrimaryKey(id);
	}
}
