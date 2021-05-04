package com.revature.dto.nasaImages;

import java.util.List;

/**
 * DTO which represents the collection JSON object gotten form the NASA API.
 */
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
