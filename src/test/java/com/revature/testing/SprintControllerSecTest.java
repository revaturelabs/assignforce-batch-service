package com.revature.testing;


import com.revature.assignforce.config.MethodSecurityConfig;
import com.revature.assignforce.controllers.ProjectController;
import com.revature.assignforce.controllers.SprintController;
import com.revature.assignforce.service.IssuesServiceProvider;
import com.revature.assignforce.service.ProjectServiceProvider;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.security.Security;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {SprintController.class, IssuesServiceProvider.class, MethodSecurityConfig.class,
        Security.class})//pattern me
@WebMvcTest(ProjectController.class)//pattern me
public class SprintControllerSecTest {

    @Configuration
    static class SprintServiceTestContextConfiguration {

        @Bean
        public IssuesServiceProvider SprintService() {
            return Mockito.mock(IssuesServiceProvider.class);
        }
    }//pattern me

    @Autowired
    MockMvc mvc;


    @Test
    public void contextLoads() {
    }

    @Test
    public void shouldDenyAnonymousUser() throws Exception {
        mvc.perform(get("/p/dog/sprints")) //change my path
                .andExpect(status().isUnauthorized());
    }

//    @Test
//    @WithMockUser(roles={"Manager of Technology"}) //sometimes change my role
//    public void shouldAllowAuthenticatedUser() throws  Exception {
//        mvc.perform(get("/p/dog/sprints")) //change my path
//                .andExpect(status().isOk());
//    }//Remark: The paths are confusing, we can't test without access to the database

    @Test
    @WithMockUser(roles={"NERDS"})
    public void rongUser() throws  Exception {
        mvc.perform(get("/p/dog/sprints")) //change my path
                .andExpect(status().isForbidden());
    }
}
