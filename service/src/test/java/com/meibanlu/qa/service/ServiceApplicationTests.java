package com.meibanlu.qa.service;

import com.meibanlu.qa.service.util.LibLoader;
import com.meibanlu.qa.service.util.TTSHelper;
import com.meibanlu.utils.MD5UTIL;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ServiceApplicationTests {

    @Test
    public void contextLoads() throws InterruptedException {
        int i = 0;
//        Thread t = new Thread(new Runnable() {
//            @Override
//            public void run() {
//                }
//        });
//        t.start();
//        Thread.sleep(120 * 1000);
        LibLoader.loadLib("msc64.dll");
        new TTSHelper().tts("0", "互联网行业的“996工作制话题持续发酵。所谓“996工作制，即早9点上班，晚9点下班，一周工作6天。对此，律师表示，企业让员工加班需要支付相应的加班费，且不论是否支付加班费，也要遵守《劳动法》中的工时制度，强制要求员工“996属于违法行为。这么算下来，按“996工作的员工在周一至周六每天均需加班2小时，与“平均每周工作时间不超过四十四小时“每月延长工作时间不得超过三十六小时的法律规定明显不符。如果企业利用“鼓励的模式变相强制要求员工加班，比如公司因员工拒绝加班而对员工降职、降薪、处罚、辞退的，员工可以向劳动保障行政部门投诉或申请劳动仲裁，维护自身合法权益。");
        i = 9;
    }

}
