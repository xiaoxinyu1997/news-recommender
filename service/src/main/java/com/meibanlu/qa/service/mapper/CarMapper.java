package com.meibanlu.qa.service.mapper;

import com.meibanlu.qa.service.entity.Car;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CarMapper {

    /**
     * 按用户ID查找所关联的车辆列表
     * @param userId 用户ID
     */
    List<Car> fetchCarInfo(int userId);

    /**
     * 保存车辆信息
     * @param car 车辆信息
     */
    int saveCarInfo(Car car);

    /**
     * 更新车辆信息
     * @param car 车辆信息
     */
    void updateCarInfo(Car car);

    /**
     * 删除车辆信息
     * @param carId 车辆信息ID
     */
    void deleteCarInfo(int carId);
}
