package com.ikould.decorate.service;

import java.util.List;

import com.ikould.decorate.entity.Material;

public interface IMaterialService {

	/**
	 * 添加
	 */
	void insert(Material material);

	/**
	 * 通过seriesId删除
	 */
	void deleteBySeriesId(int seriesId);

	/**
	 * 通过id删除
	 */
	void deleteById(int id);

	/**
	 * 通过name删除
	 */
	void deleteByName(String name);

	/**
	 * 找到所有
	 */
	List<Material> findBySeriesId(int seriesId);

	/**
	 * 查找
	 */
	Material findById(int id);

	/**
	 * 更新
	 */
	void update(Material material);

	/**
	 * 获取最大的Id
	 */
	int getMaxId();
}
