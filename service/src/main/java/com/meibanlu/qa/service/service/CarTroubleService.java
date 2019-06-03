package com.meibanlu.qa.service.service;

import com.meibanlu.qa.service.entity.CarTrouble;
import com.meibanlu.qa.service.entity.CarTroubleWrapper;
import com.meibanlu.qa.service.mapper.CarTroubleMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class CarTroubleService {

    @Resource
    private CarTroubleMapper carTroubleMapper;

    /**
     * 按条件获取车辆故障信息
     * @param timeStampStart 开始日期时间戳（包括此时间戳，即大于等于timeStampEnd）
     * @param timeStampEnd 结束日期时间戳（包括此时间戳，即小于等于timeStampEnd）
     * @param carId 车辆ID
     */
    public List<CarTroubleWrapper> fetchCarTroubleByRange(
            long timeStampStart,
            long timeStampEnd,
            int userId,
            int carId
    ){
        List<CarTroubleWrapper> result = new LinkedList<CarTroubleWrapper>();
        List<CarTrouble> carTroubleList = carTroubleMapper.fetchCarTroubleByRange(timeStampStart, timeStampEnd, userId, carId);
        if(carTroubleList == null){
            return result;
        }
        Map<Long, List<CarTrouble>> carTroubleMap = carTroubleList.stream().collect(
                Collectors.groupingBy(
                        CarTrouble::getTimeStampDay
                )
        );
        TreeMap<Long, List<CarTrouble>> carTroubleSortedMap = new TreeMap<Long, List<CarTrouble>>(carTroubleMap);
        for (Map.Entry<Long, List<CarTrouble>> entry : carTroubleSortedMap.entrySet()) {
            CarTroubleWrapper carTroubleWrapper = new CarTroubleWrapper();
            carTroubleWrapper.setUserId(userId);
            carTroubleWrapper.setCarId(carId);
            carTroubleWrapper.setDate(
                    SimpleDateFormat.getDateInstance().format(new Date(entry.getKey()))
            );
            List<CarTrouble> value = entry.getValue().stream().sorted().collect(Collectors.toList());
            carTroubleWrapper.getTroubleList(value.size()).addAll(value);
            result.add(carTroubleWrapper);
        }
        return result;
    }

    /**
     * 批量保存车辆故障信息
     * @param carTroubleWrapper 车辆故障信息集合
     */
    public void saveCarTroubleBatch(CarTroubleWrapper carTroubleWrapper){
        List<CarTrouble> carTroubles = carTroubleWrapper.getTroubleList();
        if(carTroubles.isEmpty()){
            return;
        }
        for (CarTrouble carTrouble : carTroubles) {
            carTrouble.setUserId(carTroubleWrapper.getUserId());
            carTrouble.setCarId(carTroubleWrapper.getCarId());
        }
        carTroubleMapper.saveCarTroubleBatch(carTroubles);
    }
}

