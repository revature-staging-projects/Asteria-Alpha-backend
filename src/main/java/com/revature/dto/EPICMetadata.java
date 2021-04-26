package com.revature.dto;

import com.revature.models.EPICImage;

import java.util.List;

public class EPICMetadata {

    private List<EPICImage> images_metadata;

    public List<EPICImage> getImages_metadata() {
        return images_metadata;
    }

    public void setImages_metadata(final List<EPICImage> images_metadata) {
        this.images_metadata = images_metadata;
    }
}
