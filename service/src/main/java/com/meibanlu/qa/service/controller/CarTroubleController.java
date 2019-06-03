package com.meibanlu.qa.service.controller;

import com.meibanlu.qa.service.entity.CarTroubleWrapper;
import com.meibanlu.qa.service.service.CarTroubleService;
import com.meibanlu.utils.dto.Resp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


@RestController
@RequestMapping(value = "/carFault", method = { RequestMethod.GET, RequestMethod.POST })
public class CarTroubleController {

    @Autowired
    private CarTroubleService carTroubleService;

    /**
     * 获取车辆故障信息
     * @param timeStampStart 开始日期时间戳（包括此时间戳，即大于等于timeStampEnd）
     * @param timeStampEnd 结束日期时间戳（包括此时间戳，即小于等于timeStampEnd）
     * @param carId 车辆ID
     */
    @RequestMapping(value = "/fetch")
    @ResponseBody
    public Resp fetchCarTroubleByRange(
            HttpServletRequest request,
            long timeStampStart,
            long timeStampEnd,
            int carId
    ) {
        String userId = (String) request.getSession().getAttribute("userId");
        if(userId == null){
            // 默认为1号用户
            userId = "1";
        }
        List<CarTroubleWrapper> result = carTroubleService.fetchCarTroubleByRange(
                timeStampStart,
                timeStampEnd,
                carId,
                Integer.valueOf(userId)
        );
        return Resp.success().setData(result);
    }

    /**
     * 批量保存车辆故障信息
     * @param carTroubleWrapper 车辆故障信息集合
     */
    @RequestMapping(value = "/upload/wrapper")
    @ResponseBody
    public Resp saveCarTroubleBatch(
            HttpServletRequest request,
            @RequestBody CarTroubleWrapper carTroubleWrapper
    ) {
        String userId = (String) request.getSession().getAttribute("userId");
        if(userId == null){
            // 默认为1号用户
            userId = "1";
        }
        carTroubleWrapper.setUserId(Integer.valueOf(userId));
        carTroubleService.saveCarTroubleBatch(carTroubleWrapper);
        return Resp.success().setData(carTroubleWrapper);
    }
}
