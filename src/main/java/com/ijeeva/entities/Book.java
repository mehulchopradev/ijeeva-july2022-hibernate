package com.ijeeva.entities;

import javax.persistence.*;

@Entity
@Table(name = "books")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;

    @Column(unique = true, nullable = false, length = 100)
    private String title;

    @Column(nullable = false)
    private Integer pages;

    @Column(nullable = true)
    private Float price;

    @ManyToOne
    private PublicationHouse publicationHouse;

    public Book() {}

    public Book(String title, Integer pages, Float price) {
        this.title = title;
        this.pages = pages;
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getPages() {
        return pages;
    }

    public void setPages(Integer pages) {
        this.pages = pages;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public PublicationHouse getPublicationHouse() {
        return publicationHouse;
    }

    public void setPublicationHouse(PublicationHouse publicationHouse) {
        this.publicationHouse = publicationHouse;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", pages=" + pages +
                ", price=" + price +
                '}';
    }
}
