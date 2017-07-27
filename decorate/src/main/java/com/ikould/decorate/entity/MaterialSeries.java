package com.ikould.decorate.entity;

import org.springframework.stereotype.Component;

/**
 * 素材类型下的系列
 * 
 * @author ikould
 */
@Component
public class MaterialSeries {

	// 主键
	private int id;
	// 名称
	private String name;
	// 素材类型id
	private int typeId;
	// 素材类型下的序列
	private int position;
	// 其他信息
	private String msg;

	public MaterialSeries() {
		super();
	}

	public MaterialSeries(int id, String name, int typeId, int position,
			String msg) {
		super();
		this.id = id;
		this.name = name;
		this.typeId = typeId;
		this.position = position;
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

	public int getTypeId() {
		return typeId;
	}

	public void setTypeId(int typeId) {
		this.typeId = typeId;
	}

	public int getPosition() {
		return position;
	}

	public void setPosition(int position) {
		this.position = position;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	@Override
	public String toString() {
		return "MaterialSeries [id=" + id + ", name=" + name + ", typeId="
				+ typeId + ", position=" + position + ", msg=" + msg + "]";
	}
}
