package com.revature.models.news;

import java.util.Objects;

public class Thumbnail {

    private String thumbnail;


    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(final String thumbnail) {
        this.thumbnail = thumbnail;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Thumbnail thumbnail1 = (Thumbnail) o;
        return thumbnail.equals(thumbnail1.thumbnail);
    }

    @Override
    public int hashCode() {
        return Objects.hash(thumbnail);
    }
}
