package cn.cisdigital.bitmagic.web.third.prometheus.impl;

import cn.cisdigital.bitmagic.web.third.prometheus.PrometheusClient;
import cn.cisdigital.bitmagic.web.third.prometheus.PrometheusRequest;
import cn.cisdigital.bitmagic.web.third.prometheus.PrometheusResponse;
import cn.cisdigital.bitmagic.web.third.prometheus.PrometheusValue;
import com.google.common.collect.Maps;
import lombok.SneakyThrows;
import org.junit.BeforeClass;
import org.junit.Test;

import java.text.DecimalFormat;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.Map;

/**
 * Copyright (C), 2019-2021, 中冶赛迪重庆信息技术有限公司
 * ClassName： PrometheusClientImplTest
 * Description：<p>  </p>
 *
 * @author hnbcao
 * @date 2021/11/24 3:41 下午
 */
public class PrometheusClientImplTest {

    private static PrometheusClient prometheusClient;

    @BeforeClass
    public static void init() {
        prometheusClient = new PrometheusClientImpl(5, "http://prometheus.d.cisdigital.cn/");
    }

    @Test
    public void queryRange() {
        Map<String, String> queryParam = new HashMap<>();
        queryParam.put("topic", "mirror_test2");
        PrometheusRequest request = PrometheusRequest.builder()
                .query(PrometheusRequest.QueryData.builder().query("kafka_log_logendoffset{$param}").contain(queryParam).build())
                .start(Instant.now().toEpochMilli() / 1000 - 20)
                .end(Instant.now().toEpochMilli() / 1000)
                .step(2)
                .build();
        PrometheusResponse data = prometheusClient.queryRange(request);
        System.out.println(data.toString());

    }

    @Test
    public void query() {
        Map<String, String> queryParam = new HashMap<>();
        queryParam.put("cdh", "6_3_0");
        PrometheusRequest request = PrometheusRequest.builder()
                .query(PrometheusRequest.QueryData.builder().query("push_time_seconds").contain(queryParam).build())
                .time(Instant.now().toEpochMilli())
                .build();
        PrometheusResponse data = prometheusClient.query(request);
        System.out.println(data.toString());

    }

    @SneakyThrows
    @Test
    public void queryStatusRange() {
        while (true) {
            this.queryStatus();
            Thread.sleep(30 * 1000);
        }
    }

    @Test
    public void queryStatus() {
        long now = Instant.now().toEpochMilli();
        System.out.println(now);
        PrometheusRequest request = PrometheusRequest.builder()
                .query(PrometheusRequest.QueryData.builder().query("max(push_time_seconds{cdh=\"6_3_0\", flink=\"1_11_3\"}) by (taskId)").contain(Maps.newHashMap()).build())
                .time(now)
                .build();
        Map<String, PrometheusValue> data = prometheusClient.query(request).valueByKey("taskId");
        HashMap<Integer, String> logMap = new HashMap<>();
        data.forEach((taskId, metric) -> {
            double delay = metric.getTime().doubleValue() - metric.getData() * 1000d;
            boolean timeout = delay - 30_000 > 0;
            DecimalFormat df = new DecimalFormat("##0.00");
            String log = String.format("任务ID: %s, 状态: %s, 查询时间: %s, 状态时间: %s, 延迟: %s",
                    taskId,
                    timeout,
                    df.format(metric.getTime().doubleValue()),
                    df.format(metric.getData()),
                    df.format(delay));
            logMap.put(Integer.parseInt(taskId), log);

        });
        logMap.keySet().stream().sorted().forEach(id -> System.out.println(logMap.get(id)));
    }

    @Test
    public void createTimestamp() {
        Instant now = Instant.now();
        long start = now.minus(15, ChronoUnit.DAYS).toEpochMilli();
        long end = now.toEpochMilli();
        System.out.println(start);
        System.out.println(end);
    }
}