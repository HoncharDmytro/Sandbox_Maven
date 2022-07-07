package com.sandbox.validation;

import org.joda.time.DateTime;
import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.net.URL;
import java.text.SimpleDateFormat;

public class Singer {
    private String firstName;

    @NotNull
    @Size(min = 2, max = 60)
    private String lastName;

    private DateTime birthDate;

    private URL personalSite;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public DateTime getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(DateTime birthDate) {
        this.birthDate = birthDate;
    }

    public URL getPersonalSite() {
        return personalSite;
    }

    public void setPersonalSite(URL personalSite) {
        this.personalSite = personalSite;
    }

    public String toString() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return String.format("{First name: %s, Last name: %s, Birthday: %s, Site: %s}",
                firstName, lastName, sdf.format(birthDate.toDate()), personalSite);
    }

    @AssertTrue(message="You can provide a link later")
    public boolean hasURL() {
        boolean result = false;

        if (personalSite != null) {
            result = true;
        }

        return result;
    }
}