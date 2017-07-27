package com.ikould.decorate.entity;

import org.springframework.stereotype.Component;

/**
 * 素材
 * 
 * @author ikould
 *
 */
@Component
public class Material {
	// 主键
	private int id;
	// 类型id
	private int typeId;
	// 系列id
	private int seriesId;
	// 标题
	private String title;
	// 副标题
	private String subtitle;
	// 图片地址
	private String picPath;
	// 市场价
	private String marketValue;
	// 销售价
	private String sellingValue;
	// 原价
	private String originalPrice;
	// 描述文本id
	private int textId;
	// 时间
	private long date;

	public Material() {
		super();
	}

	public Material(int id, int typeId, int seriesId, String title,
			String subtitle, String picPath, String marketValue,
			String sellingValue, String originalPrice, int textId, long date) {
		super();
		this.id = id;
		this.typeId = typeId;
		this.seriesId = seriesId;
		this.title = title;
		this.subtitle = subtitle;
		this.picPath = picPath;
		this.marketValue = marketValue;
		this.sellingValue = sellingValue;
		this.originalPrice = originalPrice;
		this.textId = textId;
		this.date = date;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getTypeId() {
		return typeId;
	}

	public void setTypeId(int typeId) {
		this.typeId = typeId;
	}

	public int getSeriesId() {
		return seriesId;
	}

	public void setSeriesId(int seriesId) {
		this.seriesId = seriesId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getSubtitle() {
		return subtitle;
	}

	public void setSubtitle(String subtitle) {
		this.subtitle = subtitle;
	}

	public String getPicPath() {
		return picPath;
	}

	public void setPicPath(String picPath) {
		this.picPath = picPath;
	}

	public String getMarketValue() {
		return marketValue;
	}

	public void setMarketValue(String marketValue) {
		this.marketValue = marketValue;
	}

	public String getSellingValue() {
		return sellingValue;
	}

	public void setSellingValue(String sellingValue) {
		this.sellingValue = sellingValue;
	}

	public String getOriginalPrice() {
		return originalPrice;
	}

	public void setOriginalPrice(String originalPrice) {
		this.originalPrice = originalPrice;
	}

	public int getTextId() {
		return textId;
	}

	public void setTextId(int textId) {
		this.textId = textId;
	}

	public long getDate() {
		return date;
	}

	public void setDate(long date) {
		this.date = date;
	}

	@Override
	public String toString() {
		return "Material [id=" + id + ", typeId=" + typeId + ", seriesId="
				+ seriesId + ", title=" + title + ", subtitle=" + subtitle
				+ ", picPath=" + picPath + ", marketValue=" + marketValue
				+ ", sellingValue=" + sellingValue + ", originalPrice="
				+ originalPrice + ", textId=" + textId + ", date=" + date + "]";
	}
}
