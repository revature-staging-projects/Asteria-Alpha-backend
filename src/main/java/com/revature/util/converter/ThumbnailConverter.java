package com.revature.util.converter;

import com.revature.models.Thumbnail;

public class ThumbnailConverter {

    public String convertToString(final Thumbnail thumbnail) {
        return thumbnail.getThumbnail();
    }

}
