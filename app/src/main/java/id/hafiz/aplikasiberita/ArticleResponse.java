package id.hafiz.aplikasiberita;

import com.google.gson.annotations.SerializedName;
import java.util.List;

public class ArticleResponse {
    @SerializedName("status")
    private String status;

    @SerializedName("articles")
    private List<Article> articles;

    public List<Article> getArticles() {
        return articles;
    }

    // Inner class untuk menampung detail artikel
    public static class Article {
        @SerializedName("title")
        private String title;

        @SerializedName("description")
        private String description;

        @SerializedName("urlToImage")
        private String urlToImage;

        @SerializedName("publishedAt")
        private String publishedAt;

        @SerializedName("author")
        private String author;

        @SerializedName("content")
        private String content;

        @SerializedName("source")
        private Source source;

        // Getters
        public String getTitle() { return title; }
        public String getDescription() { return description; }
        public String getUrlToImage() { return urlToImage; }
        public String getPublishedAt() { return publishedAt; }
        public String getAuthor() { return author; }
        public String getContent() { return content; }
        public Source getSource() { return source; }
    }

    public static class Source {
        @SerializedName("name")
        private String name;

        public String getName() { return name; }
    }
}