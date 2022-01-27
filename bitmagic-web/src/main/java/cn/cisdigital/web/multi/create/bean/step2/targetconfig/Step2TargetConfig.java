package cn.cisdigital.web.multi.create.bean.step2.targetconfig;


import cn.cisdigital.web.multi.create.bean.CdcConfigTargetDBType;
import cn.cisdigital.web.multi.create.bean.EnumDataTargetType;
import lombok.Data;

/**
 * Copyright (C), 2019-2021, 中冶赛迪重庆信息技术有限公司
 * <p>
 * ClassName： Step2DataTargetBaseConfig
 * <p>
 * Description： 目的地策略配置
 *
 * @author jh
 * @version 1.0.0
 * @date 2021/11/17 8:44
 */
@Data
@CdcConfigTargetDBType(dbType = {EnumDataTargetType.HIVE, EnumDataTargetType.MYSQL, EnumDataTargetType.ORACLE})
public class Step2TargetConfig {


}
