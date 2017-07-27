package com.ikould.decorate.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ikould.decorate.entity.MaterialText;
import com.ikould.decorate.mapper.MaterialTextMapper;
import com.ikould.decorate.service.IMaterialTextService;

@Service("materialTextService")
public class MaterialTextService implements IMaterialTextService {

	@Autowired
	MaterialTextMapper materialTextMapper;

	@Override
	public void insert(MaterialText text) {
		materialTextMapper.insert(text);
	}

	@Override
	public void deleteById(int id) {
		materialTextMapper.deleteByPrimaryKey(id);
	}

	@Override
	public MaterialText findAllByMaterialId(int materialId) {
		return materialTextMapper.findByMaterialId(materialId);
	}

	@Override
	public void update(MaterialText text) {
		materialTextMapper.updateByPrimaryKeySelective(text);
	}

	@Override
	public int getMaxId() {
		int maxId = 0;
		List<MaterialText> list = materialTextMapper.findAll();
		for (MaterialText text : list) {
			if (text.getId() > maxId) {
				maxId = text.getId();
			}
		}
		return maxId;
	}
}
