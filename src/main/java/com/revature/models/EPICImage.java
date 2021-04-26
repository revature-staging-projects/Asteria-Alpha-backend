package com.revature.models;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "EPIC_Image")
public class EPICImage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "caption")
    private String caption;

    @Column(name = "image_title")
    private String image_title;

    @Column(name = "date")
    private String image_date;

    public String getCaption() {
        return caption;
    }

    public void setCaption(final String caption) {
        this.caption = caption;
    }

    public String getImage_title() {
        return image_title;
    }

    public void setImage_title(final String image_title) {
        this.image_title = image_title;
    }

    public String getImage_date() {
        return image_date;
    }

    public void setImage_date(final String image_date) {
        this.image_date = image_date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EPICImage epicImage = (EPICImage) o;
        return caption.equals(epicImage.caption) && image_title.equals(epicImage.image_title) && image_date.equals(epicImage.image_date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(caption, image_title, image_date);
    }
}
