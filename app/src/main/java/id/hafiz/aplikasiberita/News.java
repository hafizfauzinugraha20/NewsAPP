package id.hafiz.aplikasiberita;

import java.io.Serializable;

public class News implements Serializable {
    String title;
    String category;
    String author;
    String date;
    String readTime;
    String imageUrl;
    String content;
    String summary;

    public News(String title, String category, String author, String date, String readTime, String imageUrl, String content, String summary) {
        this.title = title;
        this.category = category;
        this.author = author;
        this.date = date;
        this.readTime = readTime;
        this.imageUrl = imageUrl;
        this.content = content;
        this.summary = summary;
    }

    // Getter methods
    public String getTitle() { return title; }
    public String getCategory() { return category; }
    public String getAuthor() { return author; }
    public String getDate() { return date; }
    public String getImageUrl() { return imageUrl; }
    public String getContent() { return content; }
    public String getSummary() { return summary; }
}