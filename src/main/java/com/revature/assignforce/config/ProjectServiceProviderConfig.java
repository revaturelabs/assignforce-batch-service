package com.revature.assignforce.config;

import com.revature.assignforce.service.AsanaProjectServiceProvider;
import com.revature.assignforce.service.GitHubProjectServiceProvider;
import com.revature.assignforce.service.IssuesServiceProvider;
import com.revature.assignforce.service.ProjectServiceProvider;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.annotation.Autowire;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class ProjectServiceProviderConfig {

    @Value("${client.issues.api-key}")
    private String apiKey;

    @Value("${client.issues.provider}")
    private String issuesProvider;

    @Bean(autowire= Autowire.BY_TYPE)
    public IssuesServiceProvider issuesServiceProvider() {
        IssuesServiceProvider provider = null;
        if("github".equals(issuesProvider)) {
            provider = new GitHubProjectServiceProvider();
        } else if("asana".equals(issuesProvider)) {
            provider = new AsanaProjectServiceProvider();
        }

        if(provider != null) {
            provider.setApiKey(apiKey);
        } else {
            throw new NoSuchBeanDefinitionException("No bean for provider " + issuesProvider);
        }
        return provider;
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
