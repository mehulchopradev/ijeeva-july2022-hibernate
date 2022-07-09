package com.ijeeva.entities;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "publication_houses")
public class PublicationHouse {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @Column(nullable = false, length = 50)
    private String name;

    @Column(nullable = false)
    private Integer ratings;

    @Temporal(TemporalType.DATE)
    @Column(nullable = false)
    private Date establishedDate;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "publicationHouse")
    private Set<Book> books;

    public PublicationHouse() {}

    public PublicationHouse(String name, Integer ratings, Date establishedDate) {
        this.name = name;
        this.ratings = ratings;
        this.establishedDate = establishedDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getRatings() {
        return ratings;
    }

    public void setRatings(Integer ratings) {
        this.ratings = ratings;
    }

    public Date getEstablishedDate() {
        return establishedDate;
    }

    public void setEstablishedDate(Date establishedDate) {
        this.establishedDate = establishedDate;
    }

    public Set<Book> getBooks() {
        return books;
    }

    public void setBooks(Set<Book> books) {
        this.books = books;
    }

    @Override
    public String toString() {
        return "PublicationHouse{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", ratings=" + ratings +
                ", establishedDate=" + establishedDate +
                '}';
    }
}
