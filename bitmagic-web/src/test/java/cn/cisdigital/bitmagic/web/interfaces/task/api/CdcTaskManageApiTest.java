package cn.cisdigital.bitmagic.web.interfaces.task.api;

import cn.cisdigital.bitmagic.web.OAuthLocalhostTest;
import cn.cisdigital.bitmagic.web.common.enums.EnumDataSourceType;
import cn.cisdigital.bitmagic.web.common.enums.EnumDataTargetType;
import cn.cisdigital.bitmagic.web.common.enums.EnumHivePartitionType;
import cn.cisdigital.bitmagic.web.common.enums.EnumHudiSaveType;
import cn.cisdigital.bitmagic.web.common.enums.EnumStep2DataTacticsConfigValue;
import cn.cisdigital.bitmagic.web.common.enums.EnumStep2SchemaDeleteFieldTacticsConfigValue;
import cn.cisdigital.bitmagic.web.interfaces.task.requestDto.DirectoryMoveDto;
import cn.cisdigital.bitmagic.web.interfaces.task.requestDto.DirectoryNameDto;
import cn.cisdigital.bitmagic.web.interfaces.task.requestDto.Step1SaveRequestDto;
import cn.cisdigital.bitmagic.web.interfaces.task.requestDto.Step2SaveRequestDto;
import cn.cisdigital.bitmagic.web.interfaces.task.requestDto.Step3SaveRequestDto;
import cn.cisdigital.bitmagic.web.interfaces.task.requestDto.Step4SaveRequestDto;
import cn.cisdigital.bitmagic.web.interfaces.task.vo.cdctask.step3.Step3SelectInfoVo;
import cn.cisdigital.bitmagic.web.interfaces.task.vo.cdctask.step4.Step4SaveMappingInfoVo;
import cn.cisdigital.bitmagic.web.interfaces.task.vo.cdctask.step4.Step4TableMappingInfoVo;
import org.assertj.core.util.Lists;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CdcTaskManageApiTest extends OAuthLocalhostTest {


    @Test
    public void flinkParamGet() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get(
                                "/v1/open/api/flink-param?taskId={taskId}", 10)
                        .contentType(MediaType.APPLICATION_JSON)
                        .headers(headerWithToken()))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk());
    }



    @Test
    public void active() throws Exception {
//        org.apache.kafka.clients.producer.internals.TransactionalRequestResult;
        DirectoryMoveDto moveDto = new DirectoryMoveDto();
        moveDto.setTargetDirectoryId(1L);
        mockMvc.perform(MockMvcRequestBuilders.put(
                                "/v1/cdc-task-manage/active?taskId={taskId}", 198)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(content(moveDto))
                        .headers(headerWithToken()))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void restart() throws Exception {
        DirectoryMoveDto moveDto = new DirectoryMoveDto();
        moveDto.setTargetDirectoryId(1L);
        mockMvc.perform(MockMvcRequestBuilders.put(
                                "/v1/cdc-task-manage/restart?taskId={taskId}", 10)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(content(moveDto))
                        .headers(headerWithToken()))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void pause() throws Exception {
        DirectoryMoveDto moveDto = new DirectoryMoveDto();
        moveDto.setTargetDirectoryId(1L);
        mockMvc.perform(MockMvcRequestBuilders.put(
                                "/v1/cdc-task-manage/pause?taskId={taskId}", 10)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(content(moveDto))
                        .headers(headerWithToken()))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

}