package com.revature.assignforce.service;

import com.revature.assignforce.beans.SprintDTO;

import java.util.List;

public interface IssuesServiceProvider {
    void setApiKey(String apiKey);
    List<SprintDTO> getNativeApiSprints(String name);
}
