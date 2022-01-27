package cn.cisdigital.web.multi.create.bean.step2.sourceconfig;


import cn.cisdigital.web.multi.create.bean.CdcConfigSourceDBType;
import cn.cisdigital.web.multi.create.bean.EnumDataSourceType;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * Copyright (C), 2019-2021, 中冶赛迪重庆信息技术有限公司
 * <p>
 * ClassName： MysqlConfig
 * <p>
 * Description：
 *
 * @author jh
 * @version 1.0.0
 * @date 2021/11/16 17:20
 */
@Data
@Slf4j
@CdcConfigSourceDBType(dbType = EnumDataSourceType.MYSQL)
public class Step2SourceMysqlConfig extends Step2SourceConfig {


}
