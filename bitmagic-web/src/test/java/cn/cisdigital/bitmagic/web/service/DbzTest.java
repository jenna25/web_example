package cn.cisdigital.bitmagic.web.service;


import cn.cisdigital.bitmagic.web.third.kafka.connect.ConnectorCreateDto;
import cn.cisdigital.bitmagic.web.third.kafka.connect.ConnectorInfo;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

/**
 * Copyright (C), 2019-2021, 中冶赛迪重庆信息技术有限公司
 * <p>
 * ClassName： DbzTest
 * <p>
 * Description：
 *
 * @author jh
 * @version 1.0.0
 * @date 2021/12/23 13:57
 */

public class DbzTest {

    public static void main(String[] args) {
        RestTemplate restTemplate = new RestTemplate();
        int taskID = 8015;
        ConnectorCreateDto createDto = new ConnectorCreateDto("cdc_test_" + taskID, new HashMap<>((Map) DebeziumMysqlPropertiesKafka.genProps(taskID)));
        ResponseEntity<ConnectorInfo> response = restTemplate.exchange(
                "http://dev-master2:8083/connectors",
                HttpMethod.POST,
                new HttpEntity<>(createDto),
                ConnectorInfo.class);
//        System.out.println(response.getBody());
    }

}
