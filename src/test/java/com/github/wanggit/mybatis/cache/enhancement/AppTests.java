package com.github.wanggit.mybatis.cache.enhancement;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringRunner.class)
@SpringBootTest
@WebAppConfiguration
public class AppTests {

    MockMvc mockMvc;

    @Autowired
    private WebApplicationContext context;

    @Before
    public void init(){
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

    @Test
    public void testProjectResourceSave(){
        try {
            for (int i=0;i<10;i++){
                RequestBuilder builder = MockMvcRequestBuilders.post("/project/save")
                        .param("name", "Name" + i)
                        .param("creator", String.valueOf(i));
                MvcResult mvcResult = mockMvc.perform(builder).andReturn();
                int status = mvcResult.getResponse().getStatus();
                Assert.assertTrue(200 == status);
                String content = mvcResult.getResponse().getContentAsString();
                Assert.assertTrue("ok".equals(content));
            }
            MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/project/delete")).andReturn();
            int status = mvcResult.getResponse().getStatus();
            Assert.assertTrue(200 == status);
            String content = mvcResult.getResponse().getContentAsString();
            Assert.assertTrue("ok".equals(content));
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail(e.getMessage());
        }
    }

}
