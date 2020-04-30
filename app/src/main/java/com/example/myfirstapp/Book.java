package com.example.myfirstapp;

import com.squareup.picasso.RequestCreator;

public class Book {

    private String Title;
    private String Thumbnail;
    private String Link;


    public Book(String title, String load, String link) {
        Title = title;

        Thumbnail = load;
        Link = link;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getThumbnail() {
        return Thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        Thumbnail = thumbnail;
    }

    public String getLink() {
        return Link;
    }

    public void setLink(String link) {
        this.Link = link;
    }
}

