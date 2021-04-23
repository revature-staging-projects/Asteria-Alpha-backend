package com.revature.models;

import java.util.Objects;

public class NasaImage {
    String title;
    String description;
    String link;

    public NasaImage() {
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

    public String getLink() {
        return link;
    }

    public void setLink(final String link) {
        this.link = link;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NasaImage nasaImage = (NasaImage) o;
        return title.equals(nasaImage.title) && description.equals(nasaImage.description) && link.equals(nasaImage.link);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, description, link);
    }

    @Override
    public String toString() {
        return "NasaImage{" +
                "title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", link='" + link + '\'' +
                '}';
    }
}
