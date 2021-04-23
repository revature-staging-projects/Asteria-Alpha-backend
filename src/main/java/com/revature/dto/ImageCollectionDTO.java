package com.revature.dto;

import java.util.List;

public class ImageCollectionDTO {

    List<ImageItems> items;

    public List<ImageItems> getItems() {
        return items;
    }

    public void setItems(final List<ImageItems> items) {
        this.items = items;
    }

    public ImageCollectionDTO() {
        super();
    }
}
