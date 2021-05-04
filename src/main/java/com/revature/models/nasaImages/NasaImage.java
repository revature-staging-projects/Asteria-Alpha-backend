package com.revature.models.nasaImages;


import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "Image")
public class NasaImage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    @Column(name = "title")
    String title;

    @Column(name = "description")
    String description;

    @Column(name = "url")
    String url;

    public NasaImage() {
        super();
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

    public void setLink(final String link) {
        this.url = link;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NasaImage nasaImage = (NasaImage) o;
        return id == nasaImage.id && title.equals(nasaImage.title) && description.equals(nasaImage.description) && url.equals(nasaImage.url);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, description, url);
    }

    @Override
    public String toString() {
        return "NasaImage{" +
                "title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", link='" + url + '\'' +
                '}';
    }
}
