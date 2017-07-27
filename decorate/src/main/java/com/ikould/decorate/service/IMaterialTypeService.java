package com.ikould.decorate.service;

import java.util.List;

import com.ikould.decorate.entity.MaterialType;

public interface IMaterialTypeService {

	/**
	 * 添加
	 * 
	 * @param type
	 */
	void insert(MaterialType type);
	
	/**
	 * 获取最大Id
	 */
	int getMaxId();

	/**
	 * 通过id删除
	 * 
	 * @param id
	 */
	void deleteById(int id);

	/**
	 * 通过name删除
	 * 
	 * @param typeName
	 */
	void deleteByName(String typeName);
	
	/**
	 * 找到MaterialType
	 */
	MaterialType findById(int id);

	/**
	 * 找到所有MaterialType
	 */
	List<MaterialType> findAll();

	/**
	 * 更新MaterialType
	 * 
	 * @param type
	 */
	void update(MaterialType type);
}
