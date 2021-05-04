package com.revature.dto;

import com.revature.models.news.NewsObject;

/**
 * DTO which represents teh JSON object holding the news articles retrieved from the API.
 */
public class NewsDTO {

    private NewsObject[] value;

    public NewsObject[] getValue() {
        return value;
    }

    public void setValue(final NewsObject[] value) {
        this.value = value;
    }
}
