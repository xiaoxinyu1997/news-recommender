package com.meibanlu.qa.service.mapper;

import com.meibanlu.qa.service.entity.DriveBehavior;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface DriveBehaviorMapper {

    /**
     * 获取统计后驾驶行为信息
     * @param timeStampStart 开始日期时间戳（包括此时间戳，即大于等于timeStampEnd）
     * @param timeStampEnd 结束日期时间戳（不包括此时间戳，即小于timeStampEnd）
     * @param carId 车辆ID
     */
    List<DriveBehavior> fetchDriveBehaviorByRange(
            long timeStampStart,
            long timeStampEnd,
            int userId,
            int carId
    );

    /**
     * 获取指定时间戳的统计后驾驶行为信息
     */
    DriveBehavior fetchLastDriveBehaviorByTimeStamp(
            long timeStamp,
            int userId,
            int carId
    );

    /**
     * 获取指定时间戳之前的最后一条数据
     */
    DriveBehavior fetchLastDriveBehaviorBeforeTimeStamp(
            long timeStamp,
            int userId,
            int carId
    );

    /**
     * 保存驾驶行为信息
     * @param driveBehavior 驾驶行为信息
     */
    int saveDriveBehavior(DriveBehavior driveBehavior);

    /**
     * 保存统计的驾驶行为信息
     * @param driveBehavior 驾驶行为信息
     */
    int saveDriveBehaviorTrace(DriveBehavior driveBehavior);

    /**
     * 更新统计的驾驶行为平均速度
     * @param driveBehaviorId 统计的驾驶行为ID
     * @param avgSpd 新平均速度
     */
    int updateDriveBehaviorTraceAvgSpeed(int driveBehaviorId, float avgSpd);
}
