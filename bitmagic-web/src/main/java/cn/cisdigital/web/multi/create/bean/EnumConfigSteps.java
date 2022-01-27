package cn.cisdigital.web.multi.create.bean;


/**
 * Copyright (C), 2019-2021, 中冶赛迪重庆信息技术有限公司
 * <p>
 * ClassName： EnumDataSourceType
 * <p>
 * Description：配置步骤
 *
 * @author jh
 * @version 1.0.0
 * @date 2021/11/16 14:42
 */

public enum EnumConfigSteps {
    STEP1(1),
    STEP2(2),
    STEP3(3),
    STEP4(4);

    private int num;

    EnumConfigSteps(int num) {
        this.num = num;
    }

    public int getNum() {
        return num;
    }

}
