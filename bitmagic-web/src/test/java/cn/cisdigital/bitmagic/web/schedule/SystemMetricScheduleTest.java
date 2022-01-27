package cn.cisdigital.bitmagic.web.schedule;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 * Copyright (C), 2019-2021, 中冶赛迪重庆信息技术有限公司
 * ClassName： SystemMetricScheduleTest
 * Description：<p>  </p>
 *
 * @author hnbcao
 * @date 2021/12/28 10:54 上午
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SystemMetricScheduleTest {

    @Resource
    SystemMetricSchedule schedule;

    @Test
    public void updateSystemMetric() {
        schedule.updateSystemMetric();
    }
}