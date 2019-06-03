package com.meibanlu.qa.service.controller;

import com.meibanlu.qa.service.entity.DriveBehavior;
import com.meibanlu.qa.service.service.DriveBehaviorServiceImpl;
import com.meibanlu.utils.dto.Resp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


@RestController
@RequestMapping(value = "/driveBehavior")
public class DriveBehaviorController {

    @Autowired
    private DriveBehaviorServiceImpl driveBehaviorServiceImpl;

    /**
     * 获取驾驶行为信息
     * @param timeStampStart 开始日期时间戳（包括此时间戳，即大于等于timeStampEnd）
     * @param timeStampEnd 结束日期时间戳（包括此时间戳，即小于等于timeStampEnd）
     * @param carId 车辆ID
     */
    @RequestMapping(value = "/fetch")
    @ResponseBody
    public Resp fetchDriveBehaviorByRange(
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
        List<DriveBehavior> result = driveBehaviorServiceImpl.fetchDriveBehaviorByRange(
                timeStampStart,
                timeStampEnd,
                carId,
                Integer.valueOf(userId)
        );
        return Resp.success().setData(result);
    }

    /**
     * 保存驾驶行为信息
     * @param driveBehavior 驾驶行为信息
     */
    @RequestMapping(value = "/upload")
    @ResponseBody
    public Resp saveDriveBehavior(
            HttpServletRequest request,
            DriveBehavior driveBehavior
    ) {
        String userId = (String) request.getSession().getAttribute("userId");
        if(userId == null){
            // 默认为1号用户
            userId = "1";
        }
        driveBehavior.setUserId(Integer.valueOf(userId));
        int result = driveBehaviorServiceImpl.saveDriveBehavior(driveBehavior);
        if(result > 0){
            return Resp.success().setData(driveBehavior);
        }else{
            return Resp.error().setMsg("保存驾驶行为信息失败").setData(driveBehavior);
        }
    }
}