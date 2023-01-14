package ru.spbstu.knowledgetest.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class SecurityControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void shouldDenyAccess() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/exam-instances")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is(401));
    }

    @Test
    @WithMockUser(authorities = "ADMINISTRATOR")
    void shouldPermitAccess() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/exam-instances")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}
