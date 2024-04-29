package com.example.demo1;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name="Book")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column
    private int publishedYear;
    @Column
    private String title;
    @Column
    private String description;
    @Column
    private String category;
    @Column
    private String author;

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

    public String getDescription(){return description;}

    public void setDescription(String description){this.description = description;}

    public String getCategory(){return category; }

    public void setCategory(String category){this.category = category; }

    public String getAuthor(){return author; }

    public void setAuthor(String author){this.author = author; }

}
