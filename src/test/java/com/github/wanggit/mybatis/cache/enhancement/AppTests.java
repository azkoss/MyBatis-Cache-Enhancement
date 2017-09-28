package com.github.wanggit.mybatis.cache.enhancement;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
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
@ActiveProfiles({"test", "redisFSTConf"})
public class AppTests {

    MockMvc mockMvc;

    @Autowired
    private WebApplicationContext context;

    @Before
    public void init(){
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

    @Test
    public void testActiveProfiles(){
        String[] profiles = context.getEnvironment().getActiveProfiles();
        Assert.assertArrayEquals(profiles, new String[]{"test", "redisFSTConf"});
    }

    @Test
    public void testProjectResourceSave(){
        try {
            for (int i=0;i<20;i++){
                RequestBuilder builder = MockMvcRequestBuilders.post("/project/save")
                        .param("name", "Name" + i)
                        .param("creator", String.valueOf(i));
                MvcResult mvcResult = mockMvc.perform(builder).andReturn();
                int status = mvcResult.getResponse().getStatus();
                Assert.assertTrue(200 == status);
                String content = mvcResult.getResponse().getContentAsString();
                Assert.assertTrue("ok".equals(content));
            }

            MvcResult listResult = mockMvc.perform(MockMvcRequestBuilders.get("/project/list")).andReturn();
            int listStatus = listResult.getResponse().getStatus();
            Assert.assertTrue(200 == listStatus);
            String sizeContent = listResult.getResponse().getContentAsString();
            Assert.assertTrue(4 == Integer.parseInt(sizeContent));

            listResult = mockMvc.perform(MockMvcRequestBuilders.get("/project/list")
                    .param("name", "Name3")).andReturn();
            listStatus = listResult.getResponse().getStatus();
            Assert.assertTrue(200 == listStatus);
            sizeContent = listResult.getResponse().getContentAsString();
            Assert.assertTrue(1 == Integer.parseInt(sizeContent));

        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail(e.getMessage());
        }finally {
            try {
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

}
