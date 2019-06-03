package com.meibanlu.qa.service.service;

import com.meibanlu.qa.service.entity.DriveBehavior;
import com.meibanlu.qa.service.mapper.DriveBehaviorMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 驾驶行为Service
 */
@Service
public class DriveBehaviorServiceImpl {

    @Resource
    private DriveBehaviorMapper driveBehaviorMapper;
    /**
     * 日期加减帮助类
     */
    private GregorianCalendar gregorianCalendar = new GregorianCalendar();

    /**
     * 获取驾驶行为信息
     * @param timeStampStart 开始日期时间戳
     * @param timeStampEnd 结束日期时间戳
     * @param carId 车辆ID
     */
    public List<DriveBehavior> fetchDriveBehaviorByRange(
            long timeStampStart,
            long timeStampEnd,
            int carId,
            int userId
    ) {
        Date dateStart = fetchDay(timeStampEnd);
        gregorianCalendar.setTime(dateStart);
        gregorianCalendar.add(Calendar.DATE, 1);
        Date nextDate = gregorianCalendar.getTime();
        List<DriveBehavior> result = driveBehaviorMapper.fetchDriveBehaviorByRange(
                timeStampStart,
                nextDate.getTime(),
                userId,
                carId
        );
        if(result == null){
            result = new LinkedList<DriveBehavior>();
        }
        return result;
    }

    /**
     * 保存驾驶行为信息
     * @param driveBehavior 驾驶行为信息
     */
    public int saveDriveBehavior(DriveBehavior driveBehavior){
        int userId = driveBehavior.getUserId();
        // 如果数据没带时间戳则设置时间戳为当前时间
        if(driveBehavior.getTimeStamp() == -1L){
            driveBehavior.setTimeStamp(new Date().getTime());
        }
        Date dateStart = fetchDay(driveBehavior.getTimeStamp());
        DriveBehavior driveBehaviorLast = driveBehaviorMapper.fetchLastDriveBehaviorBeforeTimeStamp(
                dateStart.getTime(),
                userId,
                driveBehavior.getCarId()
        );
        if(driveBehaviorLast != null){
            // 保存此条数据前先计算 当天的累计行驶里程 和 当天的累计油耗
            float mile = driveBehavior.getMiles() - driveBehaviorLast.getMiles();
            if(mile < 0){
                mile = 0;
            }
            driveBehavior.setMileDay(mile);
            float fuel = driveBehavior.getFuels() - driveBehaviorLast.getFuels();
            if(fuel < 0){
                fuel = 0;
            }
            driveBehavior.setFuelDay(fuel);
        }else{
            driveBehavior.setMileDay(driveBehavior.getMiles());
            driveBehavior.setFuelDay(driveBehavior.getFuels());
        }
        try {
            updateDriveBehaviorTrace(driveBehavior);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return driveBehaviorMapper.saveDriveBehavior(driveBehavior);
    }

    /**
     * 保存统计的驾驶行为信息
     * @param driveBehavior 驾驶行为信息
     */
    private void updateDriveBehaviorTrace(DriveBehavior driveBehavior) throws Exception{
        int userId = driveBehavior.getUserId();
        Date dateStart = fetchDay(driveBehavior.getTimeStamp());
        DriveBehavior driveBehaviorTrace = driveBehaviorMapper.fetchLastDriveBehaviorByTimeStamp(
                dateStart.getTime(),
                userId,
                driveBehavior.getCarId()
        );
        if(driveBehaviorTrace == null){
            driveBehaviorTrace = (DriveBehavior) driveBehavior.clone();
            driveBehaviorTrace.setTimeStamp(dateStart.getTime());
            driveBehaviorMapper.saveDriveBehaviorTrace(driveBehaviorTrace);
        }else{
            int avgSpdCalcNum = driveBehaviorTrace.getAvgSpdCalcNum();
            float speedTotal = driveBehaviorTrace.getAvgSpd() * avgSpdCalcNum + driveBehavior.getAvgSpd();
            int num = avgSpdCalcNum + 1;
//            driveBehaviorTrace.setAvgSpd(
//                    speedTotal / num
//            );
            driveBehaviorMapper.updateDriveBehaviorTraceAvgSpeed(driveBehaviorTrace.getId(), speedTotal / num);
        }
    }

    /**
     * 根据时间戳获取精确到日的时间，即将时分秒均置为0
     * @param timeStamp 时间戳
     */
    private Date fetchDay(long timeStamp){
        Date date = new Date(timeStamp);
        try{
            String dateStr = SimpleDateFormat.getDateInstance().format(date);
            date = SimpleDateFormat.getDateInstance().parse(dateStr);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            return date;
        }
    }
}
