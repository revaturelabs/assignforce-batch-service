package com.revature.testing;

import com.revature.assignforce.service.GitHubProjectServiceProvider;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.HttpMethod;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.web.client.RestTemplate;

import static org.springframework.test.web.client.match.MockRestRequestMatchers.*;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

public class GitHubProjectServiceTest {

    private final String GITHUB_ACCEPT_VALUE = "application/vnd.github.inertia-preview+json";
    private final String GITHUB_URL_VALUE = "https://api.github.com/repos/revaturelabs/assignforce/projects";
    private final String GITHUB_KEY_VALUE = "12345678";

    RestTemplate restTemplate;

    GitHubProjectServiceProvider provider = new GitHubProjectServiceProvider();

    MockRestServiceServer mockServer;

    @Before
    public void beforeEach() {
        restTemplate = new RestTemplate();
        mockServer = MockRestServiceServer.createServer(restTemplate);
        provider.setApiKey(GITHUB_KEY_VALUE);
        provider.setRestTemplate(restTemplate);
    }

    @Test
    public void shouldSupplyHeaders() {
        mockServer.expect(requestTo(GITHUB_URL_VALUE))
                .andExpect(method(HttpMethod.GET))
                .andExpect(header("Accept", GITHUB_ACCEPT_VALUE))
                .andExpect(header("Authorization", "Bearer " + GITHUB_KEY_VALUE))
                .andRespond(withSuccess("",null));
        provider.getNativeApiSprints("assignforce");
    }
}
