package com.meibanlu.qa.service.task;

import com.meibanlu.qa.service.util.DealXCode;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@Configuration
@EnableScheduling
public class XcodeScheduleTask {

    private DealXCode dealXCode = new DealXCode();

    /**
     * 每24小时更新一次xcode
     */
    @Scheduled(fixedRate = 24 * 60 * 60 * 1000)
    private void configureTasks() {
        dealXCode.updateXcode();
    }
}
