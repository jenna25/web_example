package cn.cisdigital.bitmagic.web.service;


import cn.cisdigital.bitmagic.web.common.dto.ResponseResultDto;
import cn.cisdigital.bitmagic.web.common.util.MultiResponse;
import cn.cisdigital.bitmagic.web.interfaces.task.vo.cdctask.CdcTaskFlinkParamVo;
import cn.cisdigital.bitmagic.web.interfaces.task.vo.cdctask.step1.Step1TaskConfigParamVo;
import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.junit.Test;
import org.mockito.internal.util.collections.Sets;
import org.reflections.Reflections;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Copyright (C), 2019-2021, 中冶赛迪重庆信息技术有限公司
 * <p>
 * ClassName： MultiResponseTest
 * <p>
 * Description：
 *
 * @author jh
 * @version 1.0.0
 * @date 2021/11/23 9:01
 */

public class MultiResponseTest {

    @Test
    public void t() {
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:8020/bitmagic/v1/open/api/flink-param?taskId=10";

        HttpHeaders httpHeaders = new HttpHeaders();
        HttpEntity<?> entity = new HttpEntity<>(null, httpHeaders);
        ParameterizedTypeReference<ResponseResultDto<String>> responseType =
                new ParameterizedTypeReference<ResponseResultDto<String>>() {
                };
        ResponseEntity<ResponseResultDto<String>> response = restTemplate.exchange(url, HttpMethod.GET, entity, responseType);
        ResponseResultDto<String> responseResultDto = response.getBody();

        CdcTaskFlinkParamVo cdcTaskFlinkParamVo = JSON.parseObject(responseResultDto.getData(),CdcTaskFlinkParamVo.class);
        System.out.println(123);
    }

    @Test
    public void test() throws ClassNotFoundException {
        String baseClassName = "Step1TaskConfigParamBaseVo";
        Class<?> classType = Step1TaskConfigParamVo.class;
        List<List<FieldClassTuple>> multiFieldTupleList = getChildrenList(classType);

        for (List<FieldClassTuple> fieldClassTuples : multiFieldTupleList) {

            StringBuilder classContent = new StringBuilder("");
            // package
            classContent.append(classType.getPackage() + ".multi \n");

            // 类注解
            classAnnotationBuild(classContent, classType);

            // 类
            classContent.append(
                    "public class " + classType.getSimpleName() + " extends " + baseClassName + " {\n");

            // 循环
            for (Field field : classType.getDeclaredFields()) {
                fieldBuild(classContent, field, fieldClassTuples);
            }

            classContent.append("}");
            System.out.println(classContent.toString());
        }
    }

    private List<List<FieldClassTuple>> getChildrenList(Class<?> classType) {
        // 获取该类所有字段
        Field[] fieldList = classType.getDeclaredFields();

        List<Set<FieldClassTuple>> fieldChildrenList = Lists.newArrayListWithExpectedSize(
                fieldList.length);

        // 获取字段类型
        for (Field field : fieldList) {
            MultiResponse multiResponse = field.getAnnotation(MultiResponse.class);
            if (multiResponse == null) {
                continue;
            }
            Type fieldType = field.getGenericType();
            // 获取类型
            Class<?> fieldClass = field.getType();
            // 泛型
            if (fieldType instanceof ParameterizedType) {
                ParameterizedType parameterizedType = (ParameterizedType) fieldType;
                // 只判断单个泛型，且只有一层的情况，嵌套List不做处理
                if (parameterizedType.getActualTypeArguments()[0] instanceof Class) {
                    fieldClass = (Class<?>) parameterizedType.getActualTypeArguments()[0];
                }
            }

            // 获取字段类型的子类
            String prefix = fieldClass.getPackage().getName();
            Reflections reflections = new Reflections(prefix);
            Set<Class<?>> fieldChildrenType = Sets.newSet(fieldClass);
            fieldChildrenType.addAll(reflections.getSubTypesOf((Class<Object>) fieldClass));

            fieldChildrenList.add(
                    fieldChildrenType.stream().map(r -> new FieldClassTuple(field.getName(), r)).collect(
                            Collectors.toSet()));
        }

        // 获取各个字段不同排列组合情况
        List<List<FieldClassTuple>> pacResultList = permutationAndCombination(fieldChildrenList);
        return pacResultList;
    }

    @Data
    @AllArgsConstructor
    private static class FieldClassTuple {

        private String fieldName;
        private Class<?> classType;
    }

    private void fieldBuild(StringBuilder classContent, Field field, List<FieldClassTuple> fieldClassTuples) {
        Map<String, FieldClassTuple> fieldClassTupleMap = fieldClassTuples.stream().collect(
                Collectors.toMap(FieldClassTuple::getFieldName, r -> r));
        // 注解
        ApiModelProperty apiModelProperty = field.getAnnotation(ApiModelProperty.class);
        if (apiModelProperty != null) {
            classContent.append(
                    "@io.swagger.annotations.ApiModelProperty(\"" + apiModelProperty.value() + "\") \n");
        }

        Type fieldType = field.getGenericType();
        String name = fieldType.getTypeName();
        String result = name.replaceAll("<[^\\}]*>", "<" + "CHEMBO" + ">");

        classContent.append(Modifier.toString(field.getModifiers()) + " " + result);
        // 泛型
        if (fieldType instanceof ParameterizedType) {
            // 替换<>间的类型

        }
        // 字段名称
        classContent.append(" " + field.getName() + ";");
        classContent.append("\n");
    }

    private void classAnnotationBuild(StringBuilder classContent, Class<?> classType) {
        // 类注解
        classContent.append("@lombok.EqualsAndHashCode(callSuper = true)" + "\n");
        classContent.append("@lombok.Data" + "\n");
        // 注解
        ApiModel apiModel = classType.getAnnotation(ApiModel.class);
        if (apiModel != null) {
            classContent.append("@io.swagger.annotations.ApiModel(\"" + apiModel.value() + "\") \n");
        }
    }

    @Test
    public void comdTest() {
        Set<String> set1 = Sets.newSet("a", "b", "c");
//    Set<String> set2 = Sets.newSet("e", "f");
        Set<String> set3 = Sets.newSet("A", "B");
        List<List<String>> result = permutationAndCombination(Lists.newArrayList(set1, set3));
        System.out.println(JSON.toJSONString(result));
    }


    /**
     * 将列表中各个集合的值进行排列组合 如{"a","b","c"},{"A","B"} 得到结果为：[["a","A"],["a","B"],["b","A"],["b","B"],["c","A"],["c","B"]]
     *
     * @param list 需要排列组合的各个元素
     * @param <T>  类型
     * @return 排列组合结果
     */
    private <T> List<List<T>> permutationAndCombination(List<Set<T>> list) {
        List<List<T>> resultList = Lists.newArrayListWithExpectedSize(list.size());
        for (T t : list.get(0)) {
            List<T> result = Lists.newArrayList();
            result.add(t);
            // 递归
            addNextSet(list, 0, result, resultList);
        }
        return resultList;
    }

    /**
     * 递归
     *
     * @param list       原始排列元素列表
     * @param index      排列元素列表下标
     * @param result     每个排列组合的结果集
     * @param resultList 总的结果集
     * @param <T>        类型
     */
    private <T> void addNextSet(List<Set<T>> list, int index, List<T> result,
                                List<List<T>> resultList) {
        index++;

        for (T t : list.get(index)) {
            // 每次将上一个元素的内容复制到新的list中
            List<T> nextList = Lists.newArrayList(result);
            nextList.add(t);
            // 如果已递归到列表最后一个元素，将结果放入结果集
            if (list.size() - 1 == index) {
                resultList.add(nextList);
            } else {
                addNextSet(list, index, nextList, resultList);
            }
        }
    }

}
