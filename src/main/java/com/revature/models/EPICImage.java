package com.revature.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "EPIC_Image")
public class EPICImage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "caption")
    @JsonProperty("caption")
    private String caption;

    @Column(name = "image_title")
    @JsonProperty("image")
    private String image;

    @Column(name = "image_date")
    @JsonProperty("date")
    private String date;

    public String getCaption() {
        return caption;
    }

    public void setCaption(final String caption) {
        this.caption = caption;
    }

    public String getImage_title() {
        return image;
    }

    public void setImage_title(final String image_title) {
        this.image = image_title;
    }

    public String getImage_date() {
        return date;
    }

    public void setImage_date(final String image_date) {
        this.date = image_date.replaceAll("-","/");
        this.date = date.substring(0,date.indexOf(" "));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EPICImage epicImage = (EPICImage) o;
        return caption.equals(epicImage.caption) && image.equals(epicImage.image) && date.equals(epicImage.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(caption, image, date);
    }

    @Override
    public String toString() {
        return "EPICImage{" +
                "id=" + id +
                ", caption='" + caption + '\'' +
                ", image='" + image + '\'' +
                ", date='" + date + '\'' +
                '}';
    }
}
