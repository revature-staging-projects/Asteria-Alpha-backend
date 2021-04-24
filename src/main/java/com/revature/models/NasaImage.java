package com.revature.models;


import org.hibernate.annotations.GeneratorType;

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
    String link;

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
        return id == nasaImage.id && title.equals(nasaImage.title) && description.equals(nasaImage.description) && link.equals(nasaImage.link);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, description, link);
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
