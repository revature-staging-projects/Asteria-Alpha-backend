package com.revature.dto;

import java.util.List;

public class ImageItems {

    List<DataObject> data;
    List<LinksObject> links;

    public ImageItems() {
        super();
    }

    public List<DataObject> getData() {
        return data;
    }

    public void setData(final List<DataObject> data) {
        this.data = data;
    }

    public List<LinksObject> getLinks() {
        return links;
    }

    public void setLinks(final List<LinksObject> links) {
        this.links = links;
    }
}
