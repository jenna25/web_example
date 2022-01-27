package cn.cisdigital.bitmagic.web.service;


import cn.cisdigital.bitmagic.web.OAuthLocalhostTest;
import cn.cisdigital.bitmagic.web.common.enums.EnumDataSourceType;
import cn.cisdigital.bitmagic.web.common.enums.EnumDataTargetType;
import cn.cisdigital.bitmagic.web.component.task.entity.CdcTaskEntity;
import cn.cisdigital.bitmagic.web.component.task.repository.CdcTaskRepository;
import cn.cisdigital.bitmagic.web.interfaces.task.requestDto.Step1SaveRequestDto;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.Resource;
import org.assertj.core.util.Lists;
import org.junit.Assert;
import org.junit.Test;

/**
 * Copyright (C), 2019-2021, 中冶赛迪重庆信息技术有限公司
 * <p>
 * ClassName： CdcTaskServiceTest
 * <p>
 * Description：
 *
 * @author jh
 * @version 1.0.0
 * @date 2021/11/22 14:14
 */

public class CdcTaskEntityServiceTest extends OAuthLocalhostTest {

  @Resource
  private CdcTaskRepository taskRepository;

  @Test
  public void addTest() {
    CdcTaskEntity cdcTaskEntity = new CdcTaskEntity();
    Step1SaveRequestDto step1SaveRequestDto = new Step1SaveRequestDto();
    step1SaveRequestDto.setName("ttt");
    step1SaveRequestDto.setDirectoryId(222L);
    Map<String,Object> map = new HashMap<>();
    map.put("sourceType", EnumDataSourceType.MYSQL);
    map.put("name","tett");
    map.put("sourceId","sdfasfdafdadsfasfd");
    map.put("selected","true");
    step1SaveRequestDto.setSourceParam(Lists.newArrayList(map));

    Map<String,Object> targetmap = new HashMap<>();
    targetmap.put("targetType", EnumDataTargetType.HUDI);
    targetmap.put("name","tett");
    targetmap.put("userName","sdfasfdafdadsfasfd");
    targetmap.put("rootPath","/root/dfsdf");
    targetmap.put("selected","true");
    step1SaveRequestDto.setTargetParam(Lists.newArrayList(targetmap));

    cdcTaskEntity.taskSave(step1SaveRequestDto);

    taskRepository.save(cdcTaskEntity);
  }

  @Test
  public void queryTest(){
    CdcTaskEntity cdcTaskEntity = taskRepository.findById(1l).get();
    Assert.assertNotNull(cdcTaskEntity);
  }
}
