package kz.bitlab.techorda.db;

import java.sql.Timestamp;

public class News {
    private int id;
    private int category_id;
    private String title;
    private String content;
    private Timestamp post_date;


    public News(int id, int category_id, String title, String content, Timestamp post_date) {
        this.id = id;
        this.category_id = category_id;
        this.title = title;
        this.content = content;
        this.post_date = post_date;
    }

    public News() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCategory_id() {
        return category_id;
    }

    public void setCategory_id(int category_id) {
        this.category_id = category_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Timestamp getPost_date() {
        return post_date;
    }

    public void setPost_date(Timestamp post_date) {
        this.post_date = post_date;
    }
}
