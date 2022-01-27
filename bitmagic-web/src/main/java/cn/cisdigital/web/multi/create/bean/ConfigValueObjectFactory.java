package cn.cisdigital.web.multi.create.bean;


import cn.cisdigital.web.multi.create.bean.step2.sourceconfig.Step2SourceConfig;
import cn.cisdigital.web.multi.create.bean.step2.targetconfig.Step2TargetConfig;
import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.reflections.Reflections;
import org.reflections.scanners.Scanners;
import org.reflections.util.ConfigurationBuilder;
import org.reflections.util.FilterBuilder;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Collections;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Copyright (C), 2019-2020, 中冶赛迪重庆信息技术有限公司
 * <p>
 * ClassName： ConfigValueObjectFactory
 * <p>
 * Description：各步骤配置工厂类
 *
 * @author cjh
 * @version 1.0.0
 * @date 2020/10/15 11:05
 */
@Slf4j
public class ConfigValueObjectFactory {
    /**
     * 数据源类型集合,key为“STEP1-数据库类型"
     */
    private static Map<String, Class<?>> sourceMap;
    /**
     * 目的地类型集合,key为“STEP1-数据库类型"
     */
    private static Map<String, Class<?>> targetMap;

    private static final String CONNECT_SCHEMA = "%s-%s";

    static {
        String packageName = ConfigValueObjectFactory.class.getPackage().getName();
        Reflections reflections = new Reflections(
                new ConfigurationBuilder()
                        .forPackage(packageName)
                        .setScanners(Scanners.TypesAnnotated)
                        // mvn打包后类的路径获取时会有前缀BOOT-INF.classes.
                        .filterInputsBy(new FilterBuilder().includePackage(packageName).includePackage("BOOT-INF.classes." + packageName)));
        // 目的地类型注解
        Set<Class<?>> targetClasses = reflections.getTypesAnnotatedWith(CdcConfigTargetDBType.class);
        log.info("step config target class package{} list : {}", packageName, JSON.toJSONString(targetClasses));
        targetMap = new ConcurrentHashMap();
        for (Class<?> classObject : targetClasses) {
            CdcConfigTargetDBType configTargetAnnotation = classObject.getAnnotation(
                    CdcConfigTargetDBType.class);
            for (EnumDataTargetType targetType : configTargetAnnotation.dbType()) {
                targetMap.put(String.format(CONNECT_SCHEMA, configTargetAnnotation.steps(), targetType), classObject);
            }

        }
        targetMap = Collections.unmodifiableMap(targetMap);

        // 数据源类型注解
        Set<Class<?>> sourceClasses = reflections.getTypesAnnotatedWith(CdcConfigSourceDBType.class);
        sourceMap = new ConcurrentHashMap();
        log.info("step config source class list : {}", JSON.toJSONString(sourceClasses));
        for (Class<?> classObject : sourceClasses) {
            CdcConfigSourceDBType sourceAnnotation = classObject.getAnnotation(
                    CdcConfigSourceDBType.class);
            for (EnumDataSourceType sourceType : sourceAnnotation.dbType()) {
                sourceMap.put(String.format(CONNECT_SCHEMA, sourceAnnotation.steps(), sourceType), classObject);
            }

        }
        sourceMap = Collections.unmodifiableMap(sourceMap);
    }


    /**
     * 根据类型实例化对应配置类
     *
     * @param sourceType 数据源类型
     * @return 配置类
     */
    public static Step2SourceConfig newStep2SourceConfig(EnumDataSourceType sourceType) {
        String key = String.format(CONNECT_SCHEMA, EnumConfigSteps.STEP2, sourceType.name());
        if (sourceMap.containsKey(key)) {
            log.info("created source config type is {}", key);
            try {
                Method method = sourceMap.get(key).getMethod("create");
                return (Step2SourceConfig) method.invoke(null);
            } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
                log.error("Instantiate config failed", e);
            }
        } else {
            log.error("specified source type {} does not exist", key);

        }
        return null;
    }

    /**
     * 根据类型实例化对应目的地类
     *
     * @param targetType 目的地类型
     * @return 配置类
     */
    public static Step2TargetConfig newStep2TargetConfig(EnumDataTargetType targetType) {
        String key = String.format(CONNECT_SCHEMA, EnumConfigSteps.STEP2, targetType.name());
        if (targetMap.containsKey(key)) {
            log.debug("created target config type is {}", key);
            try {
                Method method = targetMap.get(key).getMethod("create");
                return (Step2TargetConfig) method.invoke(null);
            } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
                log.error("Instantiate target failed", e);
            }
        } else {
            log.error("specified target type {} does not exist", key);
        }
        return null;
    }


}