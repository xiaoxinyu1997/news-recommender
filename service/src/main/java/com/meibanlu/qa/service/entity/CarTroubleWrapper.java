package com.meibanlu.qa.service.entity;

import java.util.ArrayList;
import java.util.List;

public class CarTroubleWrapper {
    /**
     * 用户ID
     */
    private int userId;
    /**
     * 车辆ID
     */
    private int carId;
    /**
     * 日期
     */
    private String date;
    /**
     * 故障集合
     */
    private List<CarTrouble> troubleList;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getCarId() {
        return carId;
    }

    public void setCarId(int carId) {
        this.carId = carId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public List<CarTrouble> getTroubleList() {
        if(troubleList == null){
            troubleList = new ArrayList<>();
        }
        return troubleList;
    }

    /**
     * 按指定容量初始化故障列表
     * @param size 容量
     */
    public List<CarTrouble> getTroubleList(int size) {
        if(troubleList == null){
            troubleList = new ArrayList<>(size);
        }
        return troubleList;
    }

    public void setTroubleList(List<CarTrouble> troubleList) {
        this.troubleList = troubleList;
    }
}
