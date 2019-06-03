package com.meibanlu.qa.service.entity;


public class RealTimeData {
    /**
     * 电瓶电压
     */
    private int VBAT;
    /**
     * 发动机转速
     */
    private int PRM;
    /**
     * 车速
     */
    private int SPD;
    /**
     * 节气门开度
     */
    private int TP;
    /**
     * 发动机负荷
     */
    private int LOD;
    /**
     * 冷却液温度水温
     */
    private int ECT;
    /**
     * 油箱剩余油量
     */
    private int FLI;
    /**
     * 瞬时油耗
     */
    private int MPH;

    public int getVBAT() {
        return VBAT;
    }

    public void setVBAT(int VBAT) {
        this.VBAT = VBAT;
    }

    public int getPRM() {
        return PRM;
    }

    public void setPRM(int PRM) {
        this.PRM = PRM;
    }

    public int getSPD() {
        return SPD;
    }

    public void setSPD(int SPD) {
        this.SPD = SPD;
    }

    public int getTP() {
        return TP;
    }

    public void setTP(int TP) {
        this.TP = TP;
    }

    public int getLOD() {
        return LOD;
    }

    public void setLOD(int LOD) {
        this.LOD = LOD;
    }

    public int getECT() {
        return ECT;
    }

    public void setECT(int ECT) {
        this.ECT = ECT;
    }

    public int getFLI() {
        return FLI;
    }

    public void setFLI(int FLI) {
        this.FLI = FLI;
    }

    public int getMPH() {
        return MPH;
    }

    public void setMPH(int MPH) {
        this.MPH = MPH;
    }
}