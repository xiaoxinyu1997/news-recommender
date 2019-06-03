package com.meibanlu.qa.service.entity;

import java.sql.Timestamp;

public class UserAudiosBehavior {
    /*
 * (non-Javadoc)
 *
 * @see java.lang.Object#toString()
 */
    @Override
    public String toString() {
        return "UserAudiosBehavior [id=" + id + ", userid=" + userid + ", serviecetype=" + serviecetype
                + ", originalLength=" + originalLength + ", durationOfPlay=" + durationOfPlay + ", startTime="
                + startTime + ", endTime=" + endTime + ", audiosid=" + audiosid + "]";
    }
    private int id;
    private int userid;
    private int serviecetype;
    private int originalLength;
    private int durationOfPlay;
    private Timestamp startTime;
    private Timestamp endTime;
    private int audiosid;

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id
     *            the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the userid
     */
    public int getUserid() {
        return userid;
    }

    /**
     * @param userid
     *            the userid to set
     */
    public void setUserid(int userid) {
        this.userid = userid;
    }

    /**
     * @return the serviecetype
     */
    public int getServiecetype() {
        return serviecetype;
    }

    /**
     * @param serviecetype
     *            the serviecetype to set
     */
    public void setServiecetype(int serviecetype) {
        this.serviecetype = serviecetype;
    }

    /**
     * @return the originalLength
     */
    public int getOriginalLength() {
        return originalLength;
    }

    /**
     * @param originalLength
     *            the originalLength to set
     */
    public void setOriginalLength(int originalLength) {
        this.originalLength = originalLength;
    }

    /**
     * @return the durationOfPlay
     */
    public int getDurationOfPlay() {
        return durationOfPlay;
    }

    /**
     * @param durationOfPlay
     *            the durationOfPlay to set
     */
    public void setDurationOfPlay(int durationOfPlay) {
        this.durationOfPlay = durationOfPlay;
    }

    /**
     * @return the startTime
     */
    public Timestamp getStartTime() {
        return startTime;
    }

    /**
     * @param startTime
     *            the startTime to set
     */
    public void setStartTime(Timestamp startTime) {
        this.startTime = startTime;
    }

    /**
     * @return the endTime
     */
    public Timestamp getEndTime() {
        return endTime;
    }

    /**
     * @param endTime
     *            the endTime to set
     */
    public void setEndTime(Timestamp endTime) {
        this.endTime = endTime;
    }

    /**
     * @return the  audiosid
     */
    public int getAudiosid() {
        return audiosid;
    }

    /**
     * @param  audiosid
     *            the newsid to set
     */
    public void setAudiosid(int audiosid) {
        this.audiosid = audiosid;
    }

}
