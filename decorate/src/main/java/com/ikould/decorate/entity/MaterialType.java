package com.ikould.decorate.entity;

import org.springframework.stereotype.Component;

/**
 * 素材类型
 * 
 * @author ikould
 *
 */
@Component
public class MaterialType {
	// 主键
	private int id;
	// 名称
	private String name;
	// 其他信息
	private String msg;

	public MaterialType() {
		super();
	}

	public MaterialType(int id, String name, String msg) {
		super();
		this.id = id;
		this.name = name;
		this.msg = msg;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	@Override
	public String toString() {
		return "MaterialType [id=" + id + ", name=" + name + ", msg=" + msg
				+ "]";
	}
}
