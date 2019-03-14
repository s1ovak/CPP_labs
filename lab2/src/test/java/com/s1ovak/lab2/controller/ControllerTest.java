package com.s1ovak.lab2.controller;

import com.s1ovak.lab2.service.Service;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;


import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {Controller.class, Service.class})
public class ControllerTest {


    private MockMvc mvc;

    @Autowired
    private Service service;

    @Before
    public void set(){
        this.mvc = standaloneSetup(new Controller(service)).build();
    }




    @Test
    public void getEntity() throws Exception {
        mvc.perform(get("/api/programm?string=aasdkljgslkjaa&symbol=a")
        .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk());

    }

    @Test
    public void test200() throws Exception{
        int status = mvc.perform(get("/api/programm?string=aasdkljgslkjaa&symbol=a"))
                .andReturn().getResponse().getStatus();
        Assertions.assertThat(status).isEqualTo(HttpStatus.OK.value());
    }



    @Test
    public void test400_1() throws Exception{
        int status = mvc.perform(get("/api/programm")
                .param("string","jdfgh").param("symbol",""))
                .andReturn().getResponse().getStatus();

        Assertions.assertThat(status).isEqualTo(HttpStatus.BAD_REQUEST.value());
    }

    @Test
    public void test400_2() throws Exception{
        int status = mvc.perform(get("/api/programm")
                .param("string","").param("symbol","a"))
                .andReturn().getResponse().getStatus();

        Assertions.assertThat(status).isEqualTo(HttpStatus.BAD_REQUEST.value());
    }

    @Test
    public void test400_3() throws Exception{
        int status = mvc.perform(get("/api/programm")
                .param("string","dfgf").param("symbol","agfdgdf"))
                .andReturn().getResponse().getStatus();

        Assertions.assertThat(status).isEqualTo(HttpStatus.BAD_REQUEST.value());
    }
}