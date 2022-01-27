package cn.cisdigital.bitmagic.web.interfaces.task.api;

import cn.cisdigital.bitmagic.web.OAuthLocalhostTest;
import cn.cisdigital.bitmagic.web.interfaces.task.requestDto.DirectoryCreateDto;
import cn.cisdigital.bitmagic.web.interfaces.task.requestDto.DirectoryMoveDto;
import cn.cisdigital.bitmagic.web.interfaces.task.requestDto.DirectoryNameDto;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

public class DirectoryTreeApiTest extends OAuthLocalhostTest {

    @Test
    public void list() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get(
                                "/v1/directories/list")
                        .contentType(MediaType.APPLICATION_JSON)
                        .headers(headerWithToken()))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void createDir() throws Exception {
        DirectoryCreateDto directoryDto = new DirectoryCreateDto();
        directoryDto.setName("O(∩_∩)O哈哈~");
        directoryDto.setParentId(7L);
        mockMvc.perform(MockMvcRequestBuilders.post(
                                "/v1/directories/create-dir")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(content(directoryDto))
                        .headers(headerWithToken()))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void putName() throws Exception {
        DirectoryNameDto nameDto = new DirectoryNameDto();
        nameDto.setDirectoryName("h哈哈哈哈");
        mockMvc.perform(MockMvcRequestBuilders.put(
                                "/v1/directories/{directoryId}", 4)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(content(nameDto))
                        .headers(headerWithToken()))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void delete() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete(
                                "/v1/directories/{directoryId}", 4)
                        .contentType(MediaType.APPLICATION_JSON)
                        .headers(headerWithToken()))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void move() throws Exception {
        DirectoryMoveDto moveDto = new DirectoryMoveDto();
        moveDto.setTargetDirectoryId(5L);
        mockMvc.perform(MockMvcRequestBuilders.put(
                                "/v1/directories/{directoryId}/move", 19)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(content(moveDto))
                        .headers(headerWithToken()))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

}