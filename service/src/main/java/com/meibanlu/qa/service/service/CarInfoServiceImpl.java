package com.meibanlu.qa.service.service;

import com.meibanlu.qa.service.entity.Car;
import com.meibanlu.qa.service.mapper.CarMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.LinkedList;
import java.util.List;

@Service
public class CarInfoServiceImpl {

    @Resource
    private CarMapper carMapper;

    /**
     * 按用户ID查找所关联的车辆列表
     * @param userId 用户ID
     */
    public List<Car> fetchCarInfo(int userId){
        List<Car> result = carMapper.fetchCarInfo(userId);
        if(result == null){
            result = new LinkedList<Car>();
        }
        return result;
    }

    /**
     * 保存车辆信息
     * @param car 车辆信息
     */
    public int saveCarInfo(Car car){
        return carMapper.saveCarInfo(car);
    }

    /**
     * 更新车辆信息
     * @param car 车辆信息
     */
    public void updateCarInfo(Car car){
        carMapper.updateCarInfo(car);
    }

    /**
     * 删除车辆信息
     * @param carId 车辆信息ID
     */
    public void deleteCarInfo(int carId){
        carMapper.deleteCarInfo(carId);
    }
}
