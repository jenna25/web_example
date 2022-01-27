package cn.cisdigital.bitmagic.web.service;

import cn.cisdigital.bitmagic.web.OAuthLocalhostTest;
import cn.cisdigital.bitmagic.web.interfaces.task.service.Step4HighConfigCheckService;
import org.junit.Assert;
import org.junit.Test;
import tech.segma.cloud.auth.code.starter.PrincipalHolder;
import tech.segma.cloud.auth.code.starter.resource.UserInfoPrincipal;

import javax.annotation.Resource;

public class Step4HighConfigCheckServiceTest  extends OAuthLocalhostTest {
    @Resource
    private Step4HighConfigCheckService step4HighConfigCheckService;

    @Test
    public void checkHudiTablePathTest(){
        boolean fileExist = step4HighConfigCheckService.checkHudiTablePath("/common-lib/datax/core.json", "dev");
        Assert.assertTrue(fileExist == true);

        boolean fileNotExist = step4HighConfigCheckService.checkHudiTablePath("/common-lib/datax/core_not_exists.json", "dev");
        Assert.assertTrue(fileNotExist == false);

        boolean dirExist = step4HighConfigCheckService.checkHudiTablePath("/common-lib/datax", "dev");
        Assert.assertTrue(dirExist == true);

        boolean dirNotExist = step4HighConfigCheckService.checkHudiTablePath("/common-lib/datax_not_exists_dir", "dev");
        Assert.assertTrue(dirNotExist == false);
    }

    @Test
    public void checkHiveTableExists(){
        UserInfoPrincipal principal = new UserInfoPrincipal();
        principal.setPartyId("34453c0c710d429ea8e07dbc66dee7b6");
        PrincipalHolder.set(principal);

//        boolean tableExistsTrue = step4HighConfigCheckService.checkHiveTableExists( "test_call2");
//        Assert.assertTrue(tableExistsTrue == true);
//
//        tableExistsTrue = step4HighConfigCheckService.checkHiveTableExists( "test_call3");
//        Assert.assertTrue(tableExistsTrue == true);
//
//        boolean tableExistsFalse = step4HighConfigCheckService.checkHiveTableExists("test_not_existtable");
//        Assert.assertTrue(tableExistsFalse == false);
//
//        tableExistsFalse = step4HighConfigCheckService.checkHiveTableExists("test_not_existtable2");
//        Assert.assertTrue(tableExistsFalse == false);
    }
}
