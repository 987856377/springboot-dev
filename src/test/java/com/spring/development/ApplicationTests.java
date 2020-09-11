package com.spring.development;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultHandler;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import javax.servlet.ServletOutputStream;

import java.io.BufferedOutputStream;
import java.nio.charset.StandardCharsets;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class ApplicationTests {

    @Autowired
    private MockMvc mvc;

    @Test
    void contextLoads() {
    }

    @Test
    public void getHello() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(result -> {
                    System.out.println("RESULT CODE: " + result.getResponse().getStatus());
//                        byte[] contentAsByteArray = result.getResponse().getContentAsByteArray();
//                        String content = new String(contentAsByteArray, StandardCharsets.UTF_8);

                    String content = result.getResponse().getContentAsString(StandardCharsets.UTF_8);
                    System.out.println("RESULT BODY: " + content);

                })
                .andExpect(content().string(equalTo("Greetings from Spring Boot!")));
    }

}
