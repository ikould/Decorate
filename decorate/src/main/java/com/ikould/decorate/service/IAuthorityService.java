package com.ikould.decorate.service;

import java.util.List;

import com.ikould.decorate.entity.Authority;

public interface IAuthorityService {
	/**
	 * 添加
	 */
	void insert(Authority authority);

	/**
	 * 通过id删除
	 */
	void deleteById(int id);

	/**
	 * 查找
	 */
	Authority findById(int id);

	/**
	 * 找到所有
	 */
	List<Authority> findAll();

	/**
	 * 通过Mac地址查找
	 */
	Authority findByMac(String mac);

	/**
	 * 更新
	 */
	void update(Authority authority);
}
