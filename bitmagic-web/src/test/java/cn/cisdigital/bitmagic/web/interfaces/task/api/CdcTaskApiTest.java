package cn.cisdigital.bitmagic.web.interfaces.task.api;

import cn.cisdigital.bitmagic.web.OAuthLocalhostTest;
import cn.cisdigital.bitmagic.web.common.constants.CdcTaskConstants;
import cn.cisdigital.bitmagic.web.common.enums.EnumStep2DataTacticsConfigValue;
import cn.cisdigital.bitmagic.web.common.enums.EnumStep2SchemaDeleteFieldTacticsConfigValue;
import cn.cisdigital.bitmagic.web.interfaces.task.requestDto.*;
import cn.cisdigital.bitmagic.web.interfaces.task.vo.cdctask.step3.Step3SelectInfoVo;
import com.alibaba.fastjson.JSON;
import com.google.common.collect.Maps;
import org.assertj.core.util.Lists;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CdcTaskApiTest extends OAuthLocalhostTest {
    @Test
    public void statisticsTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get(
                                "/v1/cdc-task/task-statistics")
                        .contentType(MediaType.APPLICATION_JSON)
                        .headers(headerWithToken()))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void deleteTask() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete(
                                "/v1/cdc-task?taskId={taskId}",141)
                        .contentType(MediaType.APPLICATION_JSON)
                        .headers(headerWithToken()))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void queryTest() throws Exception {

        CdcTaskQueryRequestDto cdcTaskQueryRequestDto = new CdcTaskQueryRequestDto();
//        cdcTaskQueryRequestDto.setTaskId(5L);
//        cdcTaskQueryRequestDto.setName("es");
//        cdcTaskQueryRequestDto.setDirectoryId(222L);
//        cdcTaskQueryRequestDto.setTaskStatus(EnumCdcTaskStatus.RUNNING);
//        cdcTaskQueryRequestDto.setBeginTime(1637894998000L);
//        cdcTaskQueryRequestDto.setEndTime(1737894898000L);


        mockMvc.perform(MockMvcRequestBuilders.post(
                                "/v1/cdc-task/query?currPage=1&pageSize=10&sort=createTime,descending")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(content(cdcTaskQueryRequestDto))
                        .headers(headerWithToken()))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk());
    }


    @Test
    public void step1Get() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get(
                                "/v1/cdc-task/step1?taskId={taskId}", 5)
                        .contentType(MediaType.APPLICATION_JSON)
                        .headers(headerWithToken()))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void step1Save() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post(
                                "/v1/cdc-task/step1-save")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(content(buildStep1SaveRequestDto()))
                        .headers(headerWithToken()))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk());
    }


    @Test
    public void step2Get() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get(
                                "/v1/cdc-task/step2?taskId={taskId}", 5)
                        .contentType(MediaType.APPLICATION_JSON)
                        .headers(headerWithToken()))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void step2Save() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post(
                                "/v1/cdc-task/step2-save")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(content(buildStep2SaveRequestDto()))
                        .headers(headerWithToken()))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void step3Get() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get(
                                "/v1/cdc-task/step3?taskId={taskId}", 5)
                        .contentType(MediaType.APPLICATION_JSON)
                        .headers(headerWithToken()))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void step3Save() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post(
                                "/v1/cdc-task/step3-save")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(content(buildStep3SaveRequestDto()))
                        .headers(headerWithToken()))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void step4Get() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get(
                                "/v1/cdc-task/step4?taskId={taskId}", 140)
                        .contentType(MediaType.APPLICATION_JSON)
                        .headers(headerWithToken()))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void step4Save() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post(
                                "/v1/cdc-task/step4-save")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(content(buildStep4Save()))
                        .headers(headerWithToken()))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    private Step4SaveRequestDto buildStep4Save() {

        String json = "{\"step4MappingInfoVo\":{\"cdc-jh\":[{\"columnList\":[\"id\",\"address\",\"age\",\"hh\",\"bb\",\"cc\"],\"errors\":false,\"mapping\":{\"tableName\":\"customers2\",\"primaryKeys\":[\"id\"],\"targetTableName\":\"jh_cdc-jh_customers2\",\"columnMappingInfos\":[{\"srcName\":\"id\",\"srcType\":\"BIGINT\",\"srcComment\":\"\",\"srcSize\":19,\"srcNotNull\":true,\"targetName\":\"id\",\"targeType\":\"BIGINT\",\"targetTypeValue\":-5,\"targetComment\":\"\",\"targetSize\":19,\"targetNotNull\":true},{\"srcName\":\"address\",\"srcType\":\"VARCHAR\",\"srcComment\":\"\",\"srcSize\":255,\"srcNotNull\":true,\"targetName\":\"address\",\"targeType\":\"VARCHAR\",\"targetTypeValue\":12,\"targetComment\":\"\",\"targetSize\":255,\"targetNotNull\":true},{\"srcName\":\"age\",\"srcType\":\"VARCHAR\",\"srcComment\":\"\",\"srcSize\":255,\"srcNotNull\":true,\"targetName\":\"age\",\"targeType\":\"VARCHAR\",\"targetTypeValue\":12,\"targetComment\":\"\",\"targetSize\":255,\"targetNotNull\":true},{\"srcName\":\"hh\",\"srcType\":\"VARCHAR\",\"srcComment\":\"\",\"srcSize\":255,\"srcNotNull\":true,\"targetName\":\"hh\",\"targeType\":\"VARCHAR\",\"targetTypeValue\":12,\"targetComment\":\"\",\"targetSize\":255,\"targetNotNull\":true},{\"srcName\":\"bb\",\"srcType\":\"VARCHAR\",\"srcComment\":\"\",\"srcSize\":255,\"srcNotNull\":true,\"targetName\":\"bb\",\"targeType\":\"VARCHAR\",\"targetTypeValue\":12,\"targetComment\":\"\",\"targetSize\":255,\"targetNotNull\":true},{\"srcName\":\"cc\",\"srcType\":\"FLOAT\",\"srcComment\":\"\",\"srcSize\":12,\"srcPrecision\":0,\"srcNotNull\":true,\"targetName\":\"cc\",\"targeType\":\"FLOAT\",\"targetTypeValue\":6,\"targetComment\":\"\",\"targetSize\":12,\"targetPrecision\":0,\"targetNotNull\":true}]},\"step4HighConfigMap\":{\"errors\":false,\"saveType\":\"COW\",\"hudiTable\":\"jh_cdc_jh_customers2\",\"hudiPathError\":false,\"param\":{\"a\":\"1\"},\"hiveTable\":\"jh_cdc_jh_customers2\",\"hiveTableError\":true,\"hivePartitionType\":\"FIELD\",\"partitionKey\":\"hh\",\"timeFormat\":\"yyyy-mm-dd HH:mm:ss\",\"sourceDbName\":\"cdc-jh\"}}],\"cdc_test\":[{\"columnList\":[\"id\",\"address\",\"age\",\"hh\",\"xx\",\"tt\",\"yy1\"],\"errors\":false,\"mapping\":{\"tableName\":\"www\",\"primaryKeys\":[\"id\"],\"targetTableName\":\"jh_cdc_test_www\",\"columnMappingInfos\":[{\"srcName\":\"id\",\"srcType\":\"INT\",\"srcComment\":\"\",\"srcSize\":10,\"srcNotNull\":true,\"targetName\":\"id\",\"targeType\":\"INTEGER\",\"targetTypeValue\":4,\"targetComment\":\"\",\"targetSize\":10,\"targetNotNull\":true},{\"srcName\":\"address\",\"srcType\":\"VARCHAR\",\"srcComment\":\"\",\"srcSize\":255,\"srcNotNull\":false,\"targetName\":\"address\",\"targeType\":\"VARCHAR\",\"targetTypeValue\":12,\"targetComment\":\"\",\"targetSize\":255,\"targetNotNull\":false},{\"srcName\":\"age\",\"srcType\":\"VARCHAR\",\"srcComment\":\"\",\"srcSize\":255,\"srcNotNull\":false,\"targetName\":\"age\",\"targeType\":\"VARCHAR\",\"targetTypeValue\":12,\"targetComment\":\"\",\"targetSize\":255,\"targetNotNull\":false},{\"srcName\":\"hh\",\"srcType\":\"BIGINT\",\"srcComment\":\"\",\"srcSize\":19,\"srcNotNull\":false,\"targetName\":\"hh\",\"targeType\":\"BIGINT\",\"targetTypeValue\":-5,\"targetComment\":\"\",\"targetSize\":19,\"targetNotNull\":false},{\"srcName\":\"xx\",\"srcType\":\"VARCHAR\",\"srcComment\":\"\",\"srcSize\":255,\"srcNotNull\":false,\"targetName\":\"xx\",\"targeType\":\"VARCHAR\",\"targetTypeValue\":12,\"targetComment\":\"\",\"targetSize\":255,\"targetNotNull\":false},{\"srcName\":\"tt\",\"srcType\":\"VARCHAR\",\"srcComment\":\"\",\"srcSize\":255,\"srcNotNull\":false,\"targetName\":\"tt\",\"targeType\":\"VARCHAR\",\"targetTypeValue\":12,\"targetComment\":\"\",\"targetSize\":255,\"targetNotNull\":false},{\"srcName\":\"yy1\",\"srcType\":\"BIGINT\",\"srcComment\":\"\",\"srcSize\":19,\"srcNotNull\":false,\"targetName\":\"yy1\",\"targeType\":\"BIGINT\",\"targetTypeValue\":-5,\"targetComment\":\"\",\"targetSize\":19,\"targetNotNull\":false}]},\"step4HighConfigMap\":{\"errors\":false,\"saveType\":\"COW\",\"hudiTable\":\"jh_cdc_test_www11\",\"hudiPathError\":false,\"param\":{},\"hiveTable\":\"jh_cdc_test_www22\",\"hiveTableError\":false,\"sourceDbName\":\"cdc_test\"}}]},\"taskId\":140}";
        Step4SaveRequestDto step4SaveRequestDto = JSON.parseObject(json, Step4SaveRequestDto.class);


        return step4SaveRequestDto;
    }


    private Step3SaveRequestDto buildStep3SaveRequestDto() {
        Step3SaveRequestDto step3SaveRequestDto = new Step3SaveRequestDto();
        step3SaveRequestDto.setTaskId(8L);
        List<Step3SelectInfoVo> objects = Lists.newArrayList();
        Step3SelectInfoVo step3SelectInfoVo = new Step3SelectInfoVo();
        step3SelectInfoVo.setDbName("cdc-jh");
        step3SelectInfoVo.setTableName(Lists.newArrayList("customers1", "customers2"));
        objects.add(step3SelectInfoVo);

        Step3SelectInfoVo step3SelectInfoVo2 = new Step3SelectInfoVo();
        step3SelectInfoVo2.setDbName("lsh_test");
        step3SelectInfoVo2.setTableName(Lists.newArrayList("id_int"));
        objects.add(step3SelectInfoVo2);
        step3SaveRequestDto.setStep3SelectInfoVoList(objects);
        return step3SaveRequestDto;
    }

    private Step2SaveRequestDto buildStep2SaveRequestDto() {
        Step2SaveRequestDto step2SaveRequestDto = new Step2SaveRequestDto();
        step2SaveRequestDto.setTaskId(10L);

        // 数据源
        Map<String, Object> sourceMap = new HashMap<>();
        sourceMap.put("historyDataLoad", "true");
        sourceMap.put("logReadType", "BINLOG");
        step2SaveRequestDto.setSourceParam(sourceMap);

        // 目的地
        Map<String, Object> targetMap = new HashMap<>();
        Map<String, String> param = new HashMap<>();
        param.put("key1", "value12123");
        param.put("key2", "value2");
        targetMap.put("concurrent", 4);
        targetMap.put("timeInterval", 3);
        targetMap.put("targetConfigParam", param);
        targetMap.put("hudiTableExpression", "jh_{dbName}_{tableName}");
        targetMap.put("hudiTablePath", "path_jh_{dbName}_{tableName}");
        targetMap.put("syncHive", "syncHive");
        targetMap.put("hiveSourceId", "HIVE_INNER_JDBC_CATALOG_segma01");
        targetMap.put("hiveDataBaseName", "jd6s_w");
        targetMap.put("hiveTableExpression", "hive_{tableName}");
        step2SaveRequestDto.setTargetParam(targetMap);
        // 策略
        Map<String, Object> tacticsMap = new HashMap<>();
        tacticsMap.put("dataUpdateTactics", EnumStep2DataTacticsConfigValue.PAUSE);
        tacticsMap.put("dataAddTactics", EnumStep2DataTacticsConfigValue.PAUSE);
        tacticsMap.put("dataDeleteTactics", EnumStep2DataTacticsConfigValue.PAUSE);
        tacticsMap.put("schemaDeleteTactics", EnumStep2SchemaDeleteFieldTacticsConfigValue.BOTH_DELETE);
        tacticsMap.put("fieldAddTactics", EnumStep2DataTacticsConfigValue.PAUSE);
        tacticsMap.put("fieldDeleteTactics", EnumStep2SchemaDeleteFieldTacticsConfigValue.KEEP_TARGET);
        tacticsMap.put("fieldTypeUpdateTactics", EnumStep2DataTacticsConfigValue.PAUSE);
        tacticsMap.put("fieldNameUpdateTactics", EnumStep2DataTacticsConfigValue.PAUSE);

        step2SaveRequestDto.setTacticsParam(tacticsMap);
        return step2SaveRequestDto;
    }


    private Step1SaveRequestDto buildStep1SaveRequestDto() {

        String json = "{\"directoryId\":0,\"name\":\"eweeee12123123\",\"sourceId\":\"1635745627957\",\"sourceParam\":[{\"name\":\"dfsdf235\",\"sourceId\":\"1635745627957\",\"sourceType\":\"MYSQL\",\"selected\":true}],\"sourceType\":\"MYSQL\",\"targetParam\":[{\"name\":\"dfdfd666123\",\"targetType\":\"HUDI\",\"selected\":true,\"userName\":\"dfdfd\",\"rootPath\":null}],\"targetType\":\"HUDI\",\"taskId\":20}";

        Step1SaveRequestDto step1SaveRequestDto = new Step1SaveRequestDto();
        step1SaveRequestDto = JSON.parseObject(json, Step1SaveRequestDto.class);
//        step1SaveRequestDto.setTaskId(13L);

       /* step1SaveRequestDto.setName("阿尔萨斯");
        step1SaveRequestDto.setDirectoryId(22L);
        step1SaveRequestDto.setSourceType(EnumDataSourceType.MYSQL);
        step1SaveRequestDto.setTargetType(EnumDataTargetType.HUDI);

        step1SaveRequestDto.setTargetParam(Lists.newArrayList());
        step1SaveRequestDto.setSourceParam(Lists.newArrayList());
        // 数据源
        Map<String, Object> sourceMap = new HashMap<>();
        sourceMap.put("name", "mysql-source1111111111111111");
        sourceMap.put("selected", "true");
        sourceMap.put("sourceId", "163875861265169");
        sourceMap.put("sourceType", "MYSQL");
        step1SaveRequestDto.getSourceParam().add(sourceMap);
        Map<String, Object> sourceMap2 = new HashMap<>();
        sourceMap2.put("name", "mysql-source2");
        sourceMap2.put("selected", "false");
        sourceMap2.put("sourceId", "MYSQL_INNER_JDBC_CATALOG_segma05");
        sourceMap2.put("sourceType", "MYSQL");
        step1SaveRequestDto.getSourceParam().add(sourceMap2);

        // 目的地
        Map<String, Object> targetMap = new HashMap<>();
        targetMap.put("name", "mysql-target1");
        targetMap.put("selected", "false");
        targetMap.put("targetType", "MYSQL");
        step1SaveRequestDto.getTargetParam().add(targetMap);

        Map<String, Object> targetMap2 = new HashMap<>();
        targetMap2.put("name", "hudi-target1");
        targetMap2.put("selected", "true");
        targetMap2.put("targetType", "HUDI");
        targetMap2.put("rootPath", "tppp");
        targetMap2.put("userName", "uuuuuuuuuu");
        step1SaveRequestDto.getTargetParam().add(targetMap2);*/
        return step1SaveRequestDto;
    }

    @Test
    public void putName() throws Exception {
        DirectoryNameDto nameDto = new DirectoryNameDto();
        nameDto.setDirectoryName("h哈哈哈哈");
        mockMvc.perform(MockMvcRequestBuilders.put(
                                "/v1/cdc-task/{directoryId}", 4)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(content(nameDto))
                        .headers(headerWithToken()))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void delete() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete(
                                "/v1/cdc-task/{directoryId}", 141)
                        .contentType(MediaType.APPLICATION_JSON)
                        .headers(headerWithToken()))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void move() throws Exception {
        DirectoryMoveDto moveDto = new DirectoryMoveDto();
        moveDto.setTargetDirectoryId(1L);
        mockMvc.perform(MockMvcRequestBuilders.put(
                                "/v1/cdc-task/{directoryId}/move", 3)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(content(moveDto))
                        .headers(headerWithToken()))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void stepCfgCheck_step4HiveTableName() throws Exception {
        StepCfgCheckRequestDto dto = new StepCfgCheckRequestDto();
        dto.setStep("step4");
        dto.setTaskId(13L);
        dto.setParameters(Maps.newHashMap());
        dto.getParameters().put(CdcTaskConstants.KEY_HIVE_TARGET_TABLE_NAME, "not_exist_table_name");

        mockMvc.perform(MockMvcRequestBuilders.post(
                                "/v1/cdc-task/config-check")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(content(dto))
                        .headers(headerWithToken()))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void stepCfgCheck_step4HudiTableName() throws Exception {
        StepCfgCheckRequestDto dto = new StepCfgCheckRequestDto();
        dto.setStep("step4");
        dto.setTaskId(13L);
        dto.setParameters(Maps.newHashMap());
        dto.getParameters().put(CdcTaskConstants.KEY_HUDI_TARGET_TABLE_NAME, "not_exist_table_name");
        dto.getParameters().put(CdcTaskConstants.KEY_SOURCE_DB_NAME, "not_exist_sourceDb_name");

        mockMvc.perform(MockMvcRequestBuilders.post(
                                "/v1/cdc-task/config-check")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(content(dto))
                        .headers(headerWithToken()))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void getValidTargetDataType() throws Exception {
        ValidTargetDataTypeRequestDto dto = new ValidTargetDataTypeRequestDto();
        dto.setTaskId(13L);

        mockMvc.perform(MockMvcRequestBuilders.post(
                                "/v1/cdc-task/config-validTargetDataType")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(content(dto))
                        .headers(headerWithToken()))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void testStep4ColumnMappingInfo() throws Exception {
        Step4TargetColumnRequestDto dto = new Step4TargetColumnRequestDto();
        dto.setTaskId(11L);
        dto.setSrcDbName("cdc_test");
        dto.setSrcDbName("www");
        dto.setSrcColumnName("hh");

        mockMvc.perform(MockMvcRequestBuilders.post(
                "/v1/cdc-task/step4-columnMappingInfo")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content(dto))
                .headers(headerWithToken()))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

}