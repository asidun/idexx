package com.idexx.animana.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class SearchServiceControllerIT {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void getSearchAllServicesResultSuccess() throws Exception {
        mockMvc.perform(get("/v1/search/ed"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.[1].type").value("ALBUM"))
                .andExpect(jsonPath("$.[9].type").value("BOOK"));
    }

    @Test
    void getSearchAllServicesResultNotFound() throws Exception {
        mockMvc.perform(get("/v1/search/12ds23sdf45"))
                .andDo(print())
                .andExpect(status().isNotFound());
    }
}
