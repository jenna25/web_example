package cn.cisdigital.bitmagic.web.service;


import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.KafkaAdminClient;
import org.assertj.core.util.Lists;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * Copyright (C), 2019-2021, 中冶赛迪重庆信息技术有限公司
 * <p>
 * ClassName： KafkaClientTest
 * <p>
 * Description：
 *
 * @author jh
 * @version 1.0.0
 * @date 2021/12/30 14:46
 */

public class KafkaClientTest {

    @Test
    public void deleteTopicTest() throws ExecutionException, InterruptedException, TimeoutException {
        Properties properties = new Properties();
        properties.put("bootstrap.servers", "dev-node1:9092");

        AdminClient adminClient = KafkaAdminClient.create(properties);

//        queryCurrentTopics(adminClient);
        deleteTopics(adminClient);
    }

    List<String> queryCurrentTopics(AdminClient adminClient) throws InterruptedException, ExecutionException, TimeoutException {
        List<String> list = new ArrayList<>();
        adminClient.listTopics()
                .listings()
                .get(1, TimeUnit.MINUTES)
                .forEach(topicListing -> {
                    System.out.println("currentTopic is: " + topicListing.name());
                    list.add(topicListing.name());
                });
        return list;
    }

    void deleteTopics(AdminClient adminClient) throws ExecutionException, InterruptedException {
        adminClient.deleteTopics(Lists.newArrayList("cdc_server_22_total","cdc_server_22_changes.inventory"))
                .all()
                .get();
    }

}
