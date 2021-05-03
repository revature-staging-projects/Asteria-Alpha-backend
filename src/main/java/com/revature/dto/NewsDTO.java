package com.revature.dto;

import com.revature.models.NewsObject;

public class NewsDTO {

    private NewsObject[] value;

    public NewsObject[] getValue() {
        return value;
    }

    public void setValue(final NewsObject[] value) {
        this.value = value;
    }
}
