package com.revature.assignforce.beans;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.ArrayList;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class RevatureProBatchDTO {

    private int statusCode;
    private String description;
    private List<RevatureProData> data;
}
