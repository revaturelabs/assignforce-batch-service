package com.revature.testing;


import com.revature.assignforce.beans.Project;
import com.revature.assignforce.config.MethodSecurityConfig;
import com.revature.assignforce.controllers.BatchController;
import com.revature.assignforce.controllers.ProjectController;
import com.revature.assignforce.service.BatchService;
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
@ContextConfiguration(classes = {ProjectController.class, ProjectServiceProvider.class, MethodSecurityConfig.class,
        Security.class})//pattern me
@WebMvcTest(ProjectController.class)//pattern me
public class ProjectControllerSecTest {
    @Configuration
    static class ProjectServiceTestContextConfiguration {

        @Bean
        public ProjectServiceProvider ProjectService() {
            return Mockito.mock(ProjectServiceProvider.class);
        }
    }//pattern me

    @Autowired
    MockMvc mvc;


    @Test
    public void contextLoads() {
    }

    @Test
    public void shouldDenyAnonymousUser() throws Exception {
        mvc.perform(get("/p/")) //change my path
                .andExpect(status().isUnauthorized());
    }

    @Test
    @WithMockUser(roles={"Manager of Technology"}) //sometimes change my role
    public void shouldAllowAuthenticatedUser() throws  Exception {
        mvc.perform(get("/p/")) //change my path
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(roles={"NERDS"})
    public void rongUser() throws  Exception {
        mvc.perform(get("/p/")) //change my path
                .andExpect(status().isForbidden());
    }
}
