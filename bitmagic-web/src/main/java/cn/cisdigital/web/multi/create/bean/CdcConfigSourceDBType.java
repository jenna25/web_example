package cn.cisdigital.web.multi.create.bean;


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Copyright (C), 2019-2020, 中冶赛迪重庆信息技术有限公司
 * <p>
 * ClassName： CdcConfigSourceDBType
 * <p>
 * Description： 配置类型注解
 *
 * @author jh
 * @version 1.0.0
 * @date 2021/11/17 13:56
 */
@Target({ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface CdcConfigSourceDBType {
    EnumDataSourceType[] dbType() default {EnumDataSourceType.DB2, EnumDataSourceType.MYSQL, EnumDataSourceType.ORACLE};

    EnumConfigSteps steps() default EnumConfigSteps.STEP2;
}