package cn.cisdigital.bitmagic.web.component.log;

import cn.cisdigital.bitmagic.web.OAuthLocalhostTest;
import cn.cisdigital.bitmagic.web.common.dto.PageableSupport;
import org.junit.Test;

import javax.annotation.Resource;

public class OperationLogDomainServiceTest extends OAuthLocalhostTest {

    @Resource
    private OperationLogDomainService operationLogDomainService;

    @Test
    public void search() {
        OperationLogSearchDto searchDto = new OperationLogSearchDto();
        searchDto.setContext("分组");
        searchDto.setOperatorName("mno");
        PageableSupport support = new PageableSupport(1, 10);
        operationLogDomainService.search(1L, searchDto, support.toPageable());
    }
}