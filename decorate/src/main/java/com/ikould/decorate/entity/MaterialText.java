package com.ikould.decorate.entity;

import org.springframework.stereotype.Component;

/**
 * 素材描述文本
 * 
 * @author ikould
 */
@Component
public class MaterialText {
	// 主键
	private int id;
	// 素材id
	private int materialId;
	// 文本
	private String text;

	public MaterialText() {
		super();
	}

	public MaterialText(int id, int materialId, String text) {
		super();
		this.id = id;
		this.materialId = materialId;
		this.text = text;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getMaterialId() {
		return materialId;
	}

	public void setMaterialId(int materialId) {
		this.materialId = materialId;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	@Override
	public String toString() {
		return "MaterialText [id=" + id + ", materialId=" + materialId
				+ ", text=" + text + "]";
	}
}
