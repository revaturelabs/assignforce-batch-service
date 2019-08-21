package com.revature.assignforce.service;

import com.revature.assignforce.beans.SprintDTO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class AsanaProjectServiceProvider implements IssuesServiceProvider{
    private String apiKey;

    public AsanaProjectServiceProvider() {}

    public void setApiKey(String key) {
        this.apiKey = key;
    }

    @Override
    public List<SprintDTO> getNativeApiSprints(String name) {
        return null;
    }
}
