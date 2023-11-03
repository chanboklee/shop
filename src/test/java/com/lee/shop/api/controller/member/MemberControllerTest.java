package com.lee.shop.api.controller.member;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lee.shop.api.service.member.MemberService;
import com.lee.shop.api.service.member.request.MemberSaveServiceRequest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@ActiveProfiles("test")
@WebMvcTest(MemberController.class)
class MemberControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private MemberService memberService;

    @DisplayName("신규 회원을 가입한다.")
    @Test
    void saveMember() throws Exception {
        // given
        MemberSaveServiceRequest memberSaveServiceRequest = MemberSaveServiceRequest.builder()
                .name("이찬복")
                .email("chanboklee@naver.com")
                .password("1234")
                .build();

        // when // then
        mockMvc.perform(MockMvcRequestBuilders.post("/api/members")
                        .content(objectMapper.writeValueAsString(memberSaveServiceRequest))
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @DisplayName("가입된 회원을 전체 조회한다.")
    @Test
    void findMembers() throws Exception {
        // given
        MemberSaveServiceRequest memberSaveServiceRequest = MemberSaveServiceRequest.builder()
                .name("이찬복")
                .email("chanboklee@naver.com")
                .password("1234")
                .build();

        // when // then
        mockMvc.perform(MockMvcRequestBuilders.get("/api/members")
                        .content(objectMapper.writeValueAsString(memberSaveServiceRequest))
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}