package com.ikould.decorate.entity;

/**
 * 权限
 * <p>
 * Created by liudong on 2017/8/29.
 */

public class Authority {

	private int id;
	// 权限： 0表示禁用，1为正常
	private int authority;
	// mac地址
	private String mac;
	// 时间
	private String time;
	// 产家
	private String productName;
	// 型号
	private String modelName;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getAuthority() {
		return authority;
	}

	public void setAuthority(int authority) {
		this.authority = authority;
	}

	public String getMac() {
		return mac;
	}

	public void setMac(String mac) {
		this.mac = mac;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getModelName() {
		return modelName;
	}

	public void setModelName(String modelName) {
		this.modelName = modelName;
	}

	@Override
	public String toString() {
		return "Authority [id=" + id + ", authority=" + authority + ", mac="
				+ mac + ", time=" + time + ", productName=" + productName
				+ ", modelName=" + modelName + "]";
	}

}
