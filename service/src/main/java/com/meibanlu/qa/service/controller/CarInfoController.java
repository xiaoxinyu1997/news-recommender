package com.meibanlu.qa.service.controller;

import com.meibanlu.qa.service.entity.Car;
import com.meibanlu.qa.service.service.CarInfoServiceImpl;
import com.meibanlu.utils.dto.Resp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


@RestController
@RequestMapping(value = "/carInfo")
public class CarInfoController {

    @Autowired
    private CarInfoServiceImpl carInfoServiceImpl;

    /**
     * 获取车辆信息
     */
    @RequestMapping(value = "/fetch")
    @ResponseBody
    public Resp fetchDriveBehaviorByRange(
            HttpServletRequest request
    ) {
        String userId = (String) request.getSession().getAttribute("userId");
        if(userId == null){
            // 默认为-1号用户
            userId = "-1";
        }
        List<Car> result = carInfoServiceImpl.fetchCarInfo(
                Integer.valueOf(userId)
        );
        return Resp.success().setData(result);
    }

    /**
     * 保存车辆信息
     * @param carInfo 车辆信息
     */
    @RequestMapping(value = "/save")
    @ResponseBody
    public Resp saveDriveBehavior(
            HttpServletRequest request,
            Car carInfo
    ) {
        String userId = (String) request.getSession().getAttribute("userId");
        if(userId != null){
            carInfo.setUserId(Integer.valueOf(userId));
        }else{
            carInfo.setUserId(-1);
        }
        int result = carInfoServiceImpl.saveCarInfo(carInfo);
        if(result > 0){
            return Resp.success().setData(carInfo);
        }else{
            return Resp.error().setMsg("保存车辆信息失败").setData(carInfo);
        }
    }

    /**
     * 更新车辆信息
     * @param carInfo 车辆信息
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Resp saveDriveBehavior(
            Car carInfo
    ) {
        carInfoServiceImpl.updateCarInfo(carInfo);
        return Resp.success().setData(carInfo);
    }

    /**
     * 删除车辆信息
     * @param carId 车辆ID
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Resp saveDriveBehavior(
            int carId
    ) {
        carInfoServiceImpl.deleteCarInfo(carId);
        return Resp.success();
    }
}