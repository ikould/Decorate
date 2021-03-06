package com.ikould.decorate.mapper;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ikould.decorate.entity.MaterialSeries;

@Repository
public interface MaterialSeriesMapper {

	int deleteByTypeId(int typeId);

	int deleteByName(String name);

	List<MaterialSeries> findByTypeId(int typeId);

	List<MaterialSeries> findAll();

	/**
	 * This method was generated by MyBatis Generator. This method corresponds
	 * to the database table materialseries
	 *
	 * @mbggenerated Thu Jul 06 10:31:58 CST 2017
	 */
	int deleteByPrimaryKey(Integer id);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds
	 * to the database table materialseries
	 *
	 * @mbggenerated Thu Jul 06 10:31:58 CST 2017
	 */
	int insert(MaterialSeries record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds
	 * to the database table materialseries
	 *
	 * @mbggenerated Thu Jul 06 10:31:58 CST 2017
	 */
	int insertSelective(MaterialSeries record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds
	 * to the database table materialseries
	 *
	 * @mbggenerated Thu Jul 06 10:31:58 CST 2017
	 */
	MaterialSeries selectByPrimaryKey(Integer id);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds
	 * to the database table materialseries
	 *
	 * @mbggenerated Thu Jul 06 10:31:58 CST 2017
	 */
	int updateByPrimaryKeySelective(MaterialSeries record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds
	 * to the database table materialseries
	 *
	 * @mbggenerated Thu Jul 06 10:31:58 CST 2017
	 */
	int updateByPrimaryKey(MaterialSeries record);
}