package com.example.android.newsofindia;

public class NewsModel {

    private String title;
    private String news_image;
    private String desc;
    public String content_url;



    public NewsModel(String title, String news_image, String content, String url) {
        this.title = title;
        this.news_image = news_image;
        this.desc = content;
        this.content_url=url;
    }

    public String getTitle() {
        return title;
    }

    public String getNews_image() {
        return news_image;
    }

    public String getDesc() {
        return desc;
    }

    public String getContent_url() {
        return content_url;
    }
}

