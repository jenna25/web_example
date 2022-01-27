package cn.cisdigital.bitmagic.web.config;


import cn.cisdigital.bitmagic.web.interfaces.task.configuration.ConfigOption;
import cn.cisdigital.bitmagic.web.interfaces.task.configuration.ConfigOptions;
import cn.cisdigital.bitmagic.web.interfaces.task.configuration.Configuration;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * Copyright (C), 2019-2021, 中冶赛迪重庆信息技术有限公司
 * <p>
 * ClassName： ConfigurationTest
 * <p>
 * Description：
 *
 * @author jh
 * @version 1.0.0
 * @date 2021/12/15 9:12
 */

public class ConfigurationTest {

    @Test
    public void configTest() {

        Map<String, Object> param = new HashMap<>();

        HashMap<String, String> objectObjectHashMap = new HashMap<>();
        objectObjectHashMap.put("kkkkk", "vvvvvv");
        objectObjectHashMap.put("kkkkk1", "vvvvvv3");
        objectObjectHashMap.put("kkkkk2", "vvvvvv4");

        param.put("param", null);


        Configuration configuration = Configuration.fromMap(param);


        ConfigOptions.TypedConfigOptionBuilder<Map<String, String>> typedConfigOptionBuilder = ConfigOptions.key("param").mapType();

        ConfigOption<Map<String, String>> configOption = typedConfigOptionBuilder.noDefaultValue();
//
        Map<String, String> t = configuration.get(configOption);
        System.out.println(t);


    }

}
