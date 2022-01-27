package cn.cisdigital.bitmagic.web.interfaces.system.api;

import cn.cisdigital.bitmagic.web.OAuthLocalhostTest;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

/**
 * Copyright (C), 2019-2021, 中冶赛迪重庆信息技术有限公司
 * ClassName： MetricApiTest
 * Description：<p>  </p>
 *
 * @author hnbcao
 * @date 2021/12/2 10:51 上午
 */
public class MetricApiTest extends OAuthLocalhostTest {

    @Test
    public void queryTaskMetrics() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get(
                                "/v1/metric/task/{taskId}", 1)
                        .contentType(MediaType.APPLICATION_JSON)
                        .headers(headerWithToken()))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void queryTaskRangeMetrics() throws Exception {
        Instant now = Instant.now();
        long start = now.minus(15, ChronoUnit.DAYS).toEpochMilli();
        long end = now.toEpochMilli();
        mockMvc.perform(MockMvcRequestBuilders.get(
                                "/v1/metric/task/{taskId}/range?start={start}&end={end}", 52, start, end)
                        .contentType(MediaType.APPLICATION_JSON)
                        .headers(headerWithToken()))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void querySystemMetrics() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get(
                                "/v1/metric/system?timestamp={timestamp}", Instant.now().toEpochMilli())
                        .contentType(MediaType.APPLICATION_JSON)
                        .headers(headerWithToken()))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void querySystemRangeMetrics() throws Exception {
        Instant now = Instant.now();
        long start = now.minus(15, ChronoUnit.DAYS).toEpochMilli();
        long end = now.toEpochMilli();
        mockMvc.perform(MockMvcRequestBuilders.get(
                                "/v1/metric/system/range?start={start}&end={end}", start, end)
                        .contentType(MediaType.APPLICATION_JSON)
                        .headers(headerWithToken()))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

}