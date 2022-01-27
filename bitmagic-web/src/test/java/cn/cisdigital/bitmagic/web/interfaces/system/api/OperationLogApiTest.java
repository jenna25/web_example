package cn.cisdigital.bitmagic.web.interfaces.system.api;

import cn.cisdigital.bitmagic.web.OAuthLocalhostTest;
import cn.cisdigital.bitmagic.web.component.log.OperationLogSearchDto;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

public class OperationLogApiTest extends OAuthLocalhostTest {

    @Test
    public void listRecord() throws Exception {
        OperationLogSearchDto searchDto = new OperationLogSearchDto();
        mockMvc.perform(MockMvcRequestBuilders.post(
                                "/v1/operation-log/task/{taskId}", 10)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(content(searchDto))
                        .param("currPage", "1")
                        .param("pageSize", "10")
                        .param("sort", "createTime,ascending")
                        .headers(headerWithToken()))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}