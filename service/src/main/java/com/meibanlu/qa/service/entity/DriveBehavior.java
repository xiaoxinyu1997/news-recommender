package com.meibanlu.qa.service.entity;

/**
 * 驾驶行为
 * 注意：次实体类对应数据库中两张表，分别是 DriveBehavior和DriveBehaviorTrace
 * DriveBehavior存储原始数据
 * DriveBehaviorTrace存储统计数据，客户端显示的图表数据来自此表
 */
public class DriveBehavior implements Cloneable {
    /**
     * 生成此记录的时间戳
     */
    private long timeStamp = -1L;

    /**
     * 唯一标识
     */
    private int id;

    /**
     * 用户ID
     */
    private int userId;

    /**
     * 车辆ID
     */
    private int carId;

    /**
     * 最大转速
     */
    private int maxRpm = 0;

    /**
     * 最小转速
     */
    private int minRpm = 0;

    /**
     * 最大车速（km/h）
     */
    private float maxSpd = 0f;

    /**
     * 平均车速（km/h）
     */
    private float avgSpd = 0f;
    /**
     * 平均车速计算次数
     * 此属性用于计算平均车速，注意，此属性仅在Trace统计表中才有值
     */
    private int avgSpdCalcNum = 1;

    /**
     * 最大加速度（km/h）
     */
    private float maxAcl = 0f;

    /**
     * 此次里程（km/h）
     */
    private float mileT = 0f;

    /**
     * 此次油耗（L/h）
     */
    private float fuelT = 0f;

    /**
     * 累计总里程（km）
     */
    private float miles = 0f;

    /**
     * 当天的累计行驶里程（km）
     * 此属性无法从OBD中获取
     */
    private float mileDay = 0f;

    /**
     * 累计总油耗（L）
     */
    private float fuels = 0f;

    /**
     * 当天的累计油耗（L）
     * 此属性无法从OBD中获取
     */
    private float fuelDay = 0f;

    /**
     * 行车时间（S）
     */
    private int times = 0;

    /**
     * 点火启动次数
     */
    private int startNum = 0;

    /**
     * 刹车次数
     */
    private int brAkes = 0;

    /**
     * 急加速次数
     */
    private int racls = 0;

    /**
     * 汽车当前运行状态
     * 0：熄火
     * 1：运行
     */
    private int power;

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public long getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(long timeStamp) {
        this.timeStamp = timeStamp;
    }

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

    public int getMaxRpm() {
        return maxRpm;
    }

    public void setMaxRpm(int maxRpm) {
        this.maxRpm = maxRpm;
    }

    public int getMinRpm() {
        return minRpm;
    }

    public void setMinRpm(int minRpm) {
        this.minRpm = minRpm;
    }

    public float getMaxSpd() {
        return maxSpd;
    }

    public void setMaxSpd(float maxSpd) {
        this.maxSpd = maxSpd;
    }

    public float getAvgSpd() {
        return avgSpd;
    }

    public void setAvgSpd(float avgSpd) {
        this.avgSpd = avgSpd;
    }

    public int getAvgSpdCalcNum() {
        return avgSpdCalcNum;
    }

    public void setAvgSpdCalcNum(int avgSpdCalcNum) {
        this.avgSpdCalcNum = avgSpdCalcNum;
    }

    public int addAvgSpdCalcNum() {
        return avgSpdCalcNum++;
    }

    public float getMaxAcl() {
        return maxAcl;
    }

    public void setMaxAcl(float maxAcl) {
        this.maxAcl = maxAcl;
    }

    public float getMileT() {
        return mileT;
    }

    public void setMileT(float mileT) {
        this.mileT = mileT;
    }

    public float getFuelT() {
        return fuelT;
    }

    public void setFuelT(float fuelT) {
        this.fuelT = fuelT;
    }

    public float getMiles() {
        return miles;
    }

    public void setMiles(float miles) {
        this.miles = miles;
    }

    public float getMileDay() {
        return mileDay;
    }

    public void setMileDay(float mileDay) {
        this.mileDay = mileDay;
    }

    public float getFuels() {
        return fuels;
    }

    public void setFuels(float fuels) {
        this.fuels = fuels;
    }

    public float getFuelDay() {
        return fuelDay;
    }

    public void setFuelDay(float fuelDay) {
        this.fuelDay = fuelDay;
    }

    public int getTimes() {
        return times;
    }

    public void setTimes(int times) {
        this.times = times;
    }

    public int getStartNum() {
        return startNum;
    }

    public void setStartNum(int startNum) {
        this.startNum = startNum;
    }

    public int getBrAkes() {
        return brAkes;
    }

    public void setBrAkes(int brAkes) {
        this.brAkes = brAkes;
    }

    public int getRacls() {
        return racls;
    }

    public void setRacls(int racls) {
        this.racls = racls;
    }

    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        this.power = power;
    }
}