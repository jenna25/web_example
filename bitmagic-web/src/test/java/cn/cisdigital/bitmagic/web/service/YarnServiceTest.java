package cn.cisdigital.bitmagic.web.service;


import cn.cisdigital.bitmagic.web.OAuthLocalhostTest;
import cn.cisdigital.bitmagic.web.interfaces.task.service.CdcTaskExecuteManageService;
import cn.cisdigital.bitmagic.web.interfaces.task.service.CdcTaskMonitorService;
import cn.cisdigital.bitmagic.web.third.kafka.KafkaClient;
import cn.cisdigital.bitmagic.web.third.kafka.connect.ConnectorInfo;
import cn.cisdigital.bitmagic.web.third.yarn.YarnService;
import org.junit.Test;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * Copyright (C), 2019-2021, 中冶赛迪重庆信息技术有限公司
 * <p>
 * ClassName： CdcTaskServiceTest
 * <p>
 * Description：
 *
 * @author jh
 * @version 1.0.0
 * @date 2021/11/22 14:14
 */

public class YarnServiceTest extends OAuthLocalhostTest {

    @Resource
    private YarnService yarnService;

    @Resource
    private CdcTaskMonitorService cdcTaskMonitorService;

    @Resource
    private KafkaClient kafkaClient;

    @Resource
    private CdcTaskExecuteManageService cdcTaskExecuteManageService;

    @Test
    public void flinkErrorTest(){
        cdcTaskExecuteManageService.errorFlinkTaskProcessor(128L);
    }

    @Test
    public void createTest() {
        int taskID = 8884;
        ConnectorInfo connectorInfo = kafkaClient.connectors().create("cdc_test_" + taskID, new HashMap<>((Map) DebeziumMysqlPropertiesKafka.genProps(taskID)));
        System.out.println(connectorInfo);
    }

    @Test
    public void submitTest() {
        String jar = "hdfs://dev-master2.segma.tech:8020/user/flink/1.13.1/flink-cdc.jar";
        String main = "cn.cisdigital.bitmagic.bundle.hudi.HoodieFlinkApplication";
        yarnService.submitFlinkApplication(9983L, null, jar, main);
    }

    @Test
    public void stopTest() {
        yarnService.stopFlinkApplication("application_1639010785639_8517", 129L);
    }

    @Test
    public void monitorTest() {
        cdcTaskMonitorService.monitorDbzTask();
    }
}
