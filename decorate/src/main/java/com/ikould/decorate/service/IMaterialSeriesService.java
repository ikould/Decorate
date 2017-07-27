package com.ikould.decorate.service;

import java.util.List;

import com.ikould.decorate.entity.MaterialSeries;

public interface IMaterialSeriesService {

	/**
	 * 添加
	 */
	void insert(MaterialSeries series);

	/**
	 * 通过id删除
	 */
	void deleteById(int id);

	/**
	 * 通过typeId删除
	 */
	void deleteByTypeId(int typeId);

	/**
	 * 通过name删除
	 * 
	 * @param name
	 */
	void deleteByName(String name);

	/**
	 * 查找
	 */
	MaterialSeries findById(int id);

	/**
	 * 通过TypeId找到所有
	 */
	List<MaterialSeries> findByTypeId(int typeId);

	/**
	 * 更新
	 * 
	 * @param type
	 */
	void update(MaterialSeries series);

	/**
	 * 获取最大的Id
	 */
	int getMaxId();

	/**
	 * 获取MaterialType下最大的position
	 */
	int getMaxPosition(int typeId);
}
