package com.revature.models;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "Fav_Image")
public class FavNasaImage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    @Column(name = "title")
    String title;

    @Column(name = "description")
    String description;

    @Column(name = "url")
    String url;

    public FavNasaImage() {
    }

    public int getId() {
        return id;
    }

    public void setId(final int id) {
        this.id = id;
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
        return url;
    }

    public void setLink(final String url) {
        this.url = url;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        FavNasaImage that = (FavNasaImage) o;
        return id == that.id && title.equals(that.title) && description.equals(that.description) && url.equals(that.url);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), id, title, description, url);
    }
}
