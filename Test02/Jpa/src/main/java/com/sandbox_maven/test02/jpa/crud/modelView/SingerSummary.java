package com.sandbox_maven.test02.jpa.crud.modelView;

import java.io.Serializable;

public class SingerSummary implements Serializable {
    private final String firstName;
    private final String lastName;
    private final String latestAlbum;

    public SingerSummary(String firstName, String lastName, String latestAlbum) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.latestAlbum = latestAlbum;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getLatestAlbum() {
        return latestAlbum;
    }

    public String toString() {
        return "First name: " + firstName + ", Last Name: " + lastName
                + ", Most Recent Album: " + latestAlbum;
    }
}