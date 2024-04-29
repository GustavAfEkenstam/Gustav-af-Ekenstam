package org.example;

import com.fasterxml.jackson.annotation.JsonProperty;

public class RecordBook {

    @JsonProperty("id")
    private int id;
    @JsonProperty("publishedYear")
    private int publishedYear;
    @JsonProperty("title")
    private String title;
    @JsonProperty("description")
    private String description;
    @JsonProperty("category")
    private String category;
    @JsonProperty("author")
    private String author;
    public RecordBook(){}
    public RecordBook(int publishedYear, String title, String description, String category, String author){
        setPublishedYear(publishedYear);
        setTitle(title);
        setDescription(description);
        setCategory(category);
        setAuthor(author);
    }

    public int getId() {return id;}
    public void setId(int id){this.id = id;}
    public int getPublishedYear() {
        return publishedYear;
    }

    public void setPublishedYear(int publishedYear) {
        this.publishedYear = publishedYear;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}
