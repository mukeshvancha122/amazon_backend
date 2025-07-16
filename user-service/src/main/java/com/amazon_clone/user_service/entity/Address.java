package com.amazon_clone.user_service.entity;

import jakarta.persistence.*;
import java.util.UUID;

@Entity

public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;

    @Column
    private String street;

    @Column
    private String city;

    @Column
    private String state;

    @Column
    private String zipCode;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public User getUser() { // Updated getter
        return user;
    }

    public void setUser(User user) { // Updated setter
        this.user = user;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }
}