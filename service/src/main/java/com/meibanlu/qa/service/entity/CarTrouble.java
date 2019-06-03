package com.meibanlu.qa.service.entity;

/**
 * 车辆故障信息
 */
public class CarTrouble {
    /**
     * 用户ID
     */
    private int userId;
    /**
     * 车辆ID
     */
    private int carId;
    /**
     * 时间戳
     */
    private long timeStampCreate;
    /**
     * 时间戳
     * 精确到天
     */
    private long timeStampDay;
    /**
     * 故障编码
     */
    private String troubleCode;
    /**
     * 故障名称
     */
    private String troubleName;
    /**
     * 故障图标
     */
    private String troubleIcon;

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

    public long getTimeStampCreate() {
        return timeStampCreate;
    }

    public void setTimeStampCreate(long timeStampCreate) {
        this.timeStampCreate = timeStampCreate;
    }

    public long getTimeStampDay() {
        return timeStampDay;
    }

    public void setTimeStampDay(long timeStampDay) {
        this.timeStampDay = timeStampDay;
    }

    public String getTroubleCode() {
        return troubleCode;
    }

    public void setTroubleCode(String troubleCode) {
        this.troubleCode = troubleCode;
    }

    public String getTroubleName() {
        return troubleName;
    }

    public void setTroubleName(String troubleName) {
        this.troubleName = troubleName;
    }

    public String getTroubleIcon() {
        return troubleIcon;
    }

    public void setTroubleIcon(String troubleIcon) {
        this.troubleIcon = troubleIcon;
    }
}
