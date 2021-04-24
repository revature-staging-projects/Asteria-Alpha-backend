package com.revature.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class DataObject {

    String title;
    String description;
    @JsonProperty("keywords")
    List<String> keywords;


    public DataObject() {
        super();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(final String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(final String description) {
        this.description = description;
    }

    public List<String> getKeywords() {
        return keywords;
    }

    public void setKeywords(final List<String> keywords) {
        this.keywords = keywords;
    }
}
