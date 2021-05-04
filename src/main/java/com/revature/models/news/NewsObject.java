package com.revature.models.news;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.revature.util.converter.ThumbnailConverter;

import javax.persistence.*;

/**
 * POJO which stored into the database information about current news stories.
 */
@Entity
@Table(name = "Article")
public class NewsObject {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private int id;

    @Column(name = "title")
    private String title;

    @Column(name = "url")
    private String url;

    @Column(name = "snippet")
    private String snippet;

    @Column(name = "thumbnail_url")
    @Convert(converter = ThumbnailConverter.class)
    private Thumbnail image;


    public String getTitle() {
        return title;
    }

    public void setTitle(final String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(final String url) {
        this.url = url;
    }

    public String getSnippet() {
        return snippet;
    }

    public void setSnippet(final String snippet) {
        this.snippet = snippet;
    }

    public Thumbnail getImage() {
        return image;
    }

    public void setImage(final Thumbnail image) {
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public void setId(final int id) {
        this.id = id;
    }
}
