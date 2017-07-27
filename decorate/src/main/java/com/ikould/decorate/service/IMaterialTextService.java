package com.ikould.decorate.service;

import java.util.List;

import com.ikould.decorate.entity.MaterialText;

public interface IMaterialTextService {

	/**
	 * 添加
	 */
	void insert(MaterialText text);

	/**
	 * 通过id删除
	 */
	void deleteById(int id);

	/**
	 * 通过SeriesId查找
	 */
	MaterialText findAllByMaterialId(int materialId);

	/**
	 * 更新
	 */
	void update(MaterialText text);

	/**
	 * 获取最大的Id
	 */
	int getMaxId();
}
