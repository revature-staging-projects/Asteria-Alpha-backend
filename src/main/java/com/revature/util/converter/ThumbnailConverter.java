package com.revature.util.converter;

import com.revature.models.Thumbnail;

import javax.persistence.AttributeConverter;

public class ThumbnailConverter implements AttributeConverter<Thumbnail, String> {

    @Override
    public String convertToDatabaseColumn(final Thumbnail thumbnail) {
        return (thumbnail != null) ? thumbnail.getThumbnail(): null;
    }

    @Override
    public Thumbnail convertToEntityAttribute(final String s) {
        return null;
    }
}
