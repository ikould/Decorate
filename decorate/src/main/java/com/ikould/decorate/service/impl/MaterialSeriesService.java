package com.ikould.decorate.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ikould.decorate.entity.MaterialSeries;
import com.ikould.decorate.mapper.MaterialSeriesMapper;
import com.ikould.decorate.service.IMaterialSeriesService;

@Service("materialSeriesService")
public class MaterialSeriesService implements IMaterialSeriesService {

	@Autowired
	MaterialSeriesMapper materialSeriesMapper;

	@Override
	public void insert(MaterialSeries series) {
		materialSeriesMapper.insert(series);
	}

	@Override
	public void deleteById(int id) {
		materialSeriesMapper.deleteByPrimaryKey(id);
	}

	@Override
	public void deleteByName(String name) {
		materialSeriesMapper.deleteByName(name);
	}

	@Override
	public List<MaterialSeries> findByTypeId(int typeId) {
		return materialSeriesMapper.findByTypeId(typeId);
	}

	@Override
	public void update(MaterialSeries series) {
		materialSeriesMapper.updateByPrimaryKeySelective(series);
	}

	@Override
	public void deleteByTypeId(int typeId) {
		materialSeriesMapper.deleteByTypeId(typeId);
	}

	@Override
	public int getMaxId() {
		int maxId = 0;
		List<MaterialSeries> seriesList = materialSeriesMapper.findAll();
		for (MaterialSeries series : seriesList) {
			if (series.getId() > maxId) {
				maxId = series.getId();
			}
		}
		return maxId;
	}

	@Override
	public int getMaxPosition(int typeId) {
		int maxPosition = 0;
		List<MaterialSeries> seriesList = materialSeriesMapper
				.findByTypeId(typeId);
		for (MaterialSeries series : seriesList) {
			if (series.getPosition() > maxPosition) {
				maxPosition = series.getId();
			}
		}
		return maxPosition;
	}

	@Override
	public MaterialSeries findById(int id) {
		return materialSeriesMapper.selectByPrimaryKey(id);
	}
}
