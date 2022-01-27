package cn.cisdigital.web.multi.create.bean.step2.targetconfig;


import cn.cisdigital.web.multi.create.bean.CdcConfigTargetDBType;
import cn.cisdigital.web.multi.create.bean.EnumDataTargetType;
import lombok.Data;

/**
 * Copyright (C), 2019-2021, 中冶赛迪重庆信息技术有限公司
 * <p>
 * ClassName： Step2TargetHudiConfig
 * <p>
 * Description：
 *
 * @author jh
 * @version 1.0.0
 * @date 2021/11/16 17:20
 */
@Data
@CdcConfigTargetDBType(dbType = EnumDataTargetType.HUDI)
public class Step2TargetHudiConfig extends Step2TargetConfig {

}