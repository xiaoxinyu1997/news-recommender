package com.meibanlu.qa.service.entity;

/**
 * 车辆信息
 */
public class Car {

    /**
     * 唯一标识
     */
    private int id;
    /**
     * 用户ID
     */
    private int userId;
    /**
     * 车牌号码
     */
    private String carNumber = "暂无";
    /**
     * 汽车品牌
     */
    private String carBrand = "暂无";
    /**
     * 汽车型号
     */
    private String carModel = "暂无";
    /**
     * 购买日期
     */
    private String dateOfBuy = "暂无";
    /**
     * 所在城市
     */
    private String carCity = "暂无";

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getCarNumber() {
        return carNumber;
    }

    public void setCarNumber(String carNumber) {
        this.carNumber = carNumber;
    }

    public String getCarBrand() {
        return carBrand;
    }

    public void setCarBrand(String carBrand) {
        this.carBrand = carBrand;
    }

    public String getCarModel() {
        return carModel;
    }

    public void setCarModel(String carModel) {
        this.carModel = carModel;
    }

    public String getDateOfBuy() {
        return dateOfBuy;
    }

    public void setDateOfBuy(String dateOfBuy) {
        this.dateOfBuy = dateOfBuy;
    }

    public String getCarCity() {
        return carCity;
    }

    public void setCarCity(String carCity) {
        this.carCity = carCity;
    }
}
