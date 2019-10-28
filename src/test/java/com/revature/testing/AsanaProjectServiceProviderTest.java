package com.revature.testing;

import org.junit.Before;
import org.junit.Test;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.web.client.RestTemplate;

import com.revature.assignforce.service.AsanaProjectServiceProvider;

public class AsanaProjectServiceProviderTest {
	
    RestTemplate restTemplate;

    AsanaProjectServiceProvider provider = new AsanaProjectServiceProvider();

    MockRestServiceServer mockServer;

    @Before
    public void beforeEach() {
        restTemplate = new RestTemplate();
        mockServer = MockRestServiceServer.createServer(restTemplate);
        provider.setApiKey("test");
    }
    
    @Test
    public void testGetNativeApiSprints() {
    	provider.getNativeApiSprints("name");
    	
    	/**
    	 * getNativeApiSprints() in AsanaProjectServiceProvider needs to be implemented
    	 * before it can be tested.
    	 */
    	
    }

}
