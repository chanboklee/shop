package com.lee.shop.api.controller.member;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lee.shop.api.controller.member.request.MemberSaveRequest;
import com.lee.shop.api.service.member.MemberService;
import com.lee.shop.api.service.member.request.MemberSaveServiceRequest;
import com.lee.shop.api.service.member.response.MemberResponse;
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

import java.util.List;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

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
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$.code").value("200"))
                .andExpect(jsonPath("$.httpStatus").value("OK"))
                .andExpect(jsonPath("$.message").value("OK"));
    }

    @DisplayName("신규 회원가입을 할 때 이름은 필수값이다.")
    @Test
    void saveMemberWithoutName() throws Exception {
        // given
        MemberSaveRequest memberSaveRequest = MemberSaveRequest.builder()
                .email("chanboklee@naver.com")
                .password("1234")
                .build();

        // when // then
        mockMvc.perform(MockMvcRequestBuilders.post("/api/members")
                        .content(objectMapper.writeValueAsString(memberSaveRequest))
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(jsonPath("$.code").value("400"))
                .andExpect(jsonPath("$.httpStatus").value("BAD_REQUEST"))
                .andExpect(jsonPath("$.message").value("이름은 필수입니다."));
    }

    @DisplayName("신규 회원가입을 할 때 이메일은 필수값이다.")
    @Test
    void saveMemberWithoutEmail() throws Exception {
        // given
        MemberSaveRequest memberSaveRequest = MemberSaveRequest.builder()
                .name("이찬복")
                .password("1234")
                .build();

        // when // then
        mockMvc.perform(MockMvcRequestBuilders.post("/api/members")
                        .content(objectMapper.writeValueAsString(memberSaveRequest))
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$.code").value("400"))
                .andExpect(jsonPath("$.httpStatus").value("BAD_REQUEST"))
                .andExpect(jsonPath("$.message").value("이메일은 필수입니다."));
    }

    @DisplayName("신규 회원가입을 할 때 패스워드는 필수값이다.")
    @Test
    void saveMemberWithoutPassword() throws Exception {
        // given
        MemberSaveRequest memberSaveRequest = MemberSaveRequest.builder()
                .name("이찬복")
                .email("chanboklee@naver.com")
                .build();

        // when // then
        mockMvc.perform(MockMvcRequestBuilders.post("/api/members")
                        .content(objectMapper.writeValueAsString(memberSaveRequest))
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$.code").value("400"))
                .andExpect(jsonPath("$.httpStatus").value("BAD_REQUEST"))
                .andExpect(jsonPath("$.message").value("패스워드는 필수입니다."));
    }


    @DisplayName("가입된 회원을 전체 조회한다.")
    @Test
    void findMembers() throws Exception {
        // given
        List<MemberResponse> members = List.of();
        given(memberService.findMembers()).willReturn(members);

        // when // then
        mockMvc.perform(MockMvcRequestBuilders.get("/api/members"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$.code").value("200"))
                .andExpect(jsonPath("$.httpStatus").value("OK"))
                .andExpect(jsonPath("$.message").value("OK"))
                .andExpect(jsonPath("$.data").isArray());
    }

    @DisplayName("회원 ID를 기준으로 회원을 조회한다.")
    @Test
    void findMember() throws Exception {
        // given
        MemberResponse memberResponse = MemberResponse.builder()
                .name("이찬복")
                .email("chanboklee@naver.com")
                .password("1234")
                .build();

        given(memberService.findMember(anyLong())).willReturn(memberResponse);

        // when // then
        mockMvc.perform(MockMvcRequestBuilders.get("/api/members/{id}", anyLong()))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$.code").value("200"))
                .andExpect(jsonPath("$.httpStatus").value("OK"))
                .andExpect(jsonPath("$.message").value("OK"))
                .andExpect(jsonPath("$.data").exists())
                .andExpect(jsonPath("$.data.name").value("이찬복"))
                .andExpect(jsonPath("$.data.email").value("chanboklee@naver.com"))
                .andExpect(jsonPath("$.data.password").value("1234"));
    }
}