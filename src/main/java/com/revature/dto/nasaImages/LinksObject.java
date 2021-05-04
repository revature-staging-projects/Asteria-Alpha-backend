package com.revature.dto.nasaImages;

/**
 * DTO which represnet the JSON object for the image url from the NASA API.
 */
public class LinksObject {

    String href;

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public LinksObject() {
        super();
    }
}
