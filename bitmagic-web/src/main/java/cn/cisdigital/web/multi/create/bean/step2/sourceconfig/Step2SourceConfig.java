package cn.cisdigital.web.multi.create.bean.step2.sourceconfig;


import cn.cisdigital.web.multi.create.bean.CdcConfigSourceDBType;
import cn.cisdigital.web.multi.create.bean.EnumConfigSteps;
import cn.cisdigital.web.multi.create.bean.EnumDataSourceType;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * Copyright (C), 2019-2021, 中冶赛迪重庆信息技术有限公司
 * <p>
 * ClassName： Step2DataSourceConfig
 * <p>
 * Description：步骤2数据源配置
 *
 * @author jh
 * @version 1.0.0
 * @date 2021/11/16 17:24
 */
@Data
@Slf4j
@CdcConfigSourceDBType(steps = EnumConfigSteps.STEP2, dbType = {EnumDataSourceType.DB2, EnumDataSourceType.ORACLE})
public class Step2SourceConfig {


}
