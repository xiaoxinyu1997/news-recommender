package com.meibanlu.qa.service.mapper;

import com.meibanlu.qa.service.entity.CarTrouble;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CarTroubleMapper {

    /**
     * 按条件获取车辆故障信息
     * @param timeStampStart 开始日期时间戳（包括此时间戳，即大于等于timeStampEnd）
     * @param timeStampEnd 结束日期时间戳（包括此时间戳，即小于等于timeStampEnd）
     * @param carId 车辆ID
     */
    List<CarTrouble> fetchCarTroubleByRange(
            long timeStampStart,
            long timeStampEnd,
            int userId,
            int carId
    );

    /**
     * 保存车辆故障信息
     * @param carTrouble 车辆故障信息
     */
    int saveCarTrouble(CarTrouble carTrouble);

    /**
     * 批量保存车辆故障信息
     * @param carTroubleList 车辆故障信息集合
     */
    int saveCarTroubleBatch(List<CarTrouble> carTroubleList);

    /**
     * 删除车辆故障信息
     * @param userId 用户ID
     * @param carId 车辆ID
     * @param id 故障ID
     */
    void deleteCarTrouble(
            int userId,
            int carId,
            int id
    );
}
