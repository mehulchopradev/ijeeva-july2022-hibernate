package com.ijeeva.entities;

import javax.persistence.*;

@Entity
@Table(name = "locations")
public class Location {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @Column(nullable = false, length = 20)
    private String country;

    @Column(nullable = false, length = 20)
    private String state;

    @Column(nullable = true)
    private Integer postalCode;

    @OneToOne(mappedBy = "location", fetch = FetchType.LAZY)
    private Author author;

    public Location() {}

    public Location(String country, String state, Integer postalCode) {
        this.country = country;
        this.state = state;
        this.postalCode = postalCode;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Integer getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(Integer postalCode) {
        this.postalCode = postalCode;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    @Override
    public String toString() {
        return "Location{" +
                "id=" + id +
                ", country='" + country + '\'' +
                ", state='" + state + '\'' +
                ", postalCode=" + postalCode +
                '}';
    }
}
