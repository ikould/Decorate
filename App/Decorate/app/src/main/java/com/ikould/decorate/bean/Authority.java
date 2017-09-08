package com.ikould.decorate.bean;

/**
 * 权限
 * <p>
 * Created by liudong on 2017/8/29.
 */

public class Authority {

    private int    id;
    // 序列
    private int    pos;
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

    public int getPos() {
        return pos;
    }

    public void setPos(int pos) {
        this.pos = pos;
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
        return "UpdateBean{" +
                "id=" + id +
                ", pos=" + pos +
                ", mac='" + mac + '\'' +
                ", time='" + time + '\'' +
                ", productName='" + productName + '\'' +
                ", modelName='" + modelName + '\'' +
                '}';
    }
}
