package com.sandbox.web.base.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

import static jakarta.persistence.GenerationType.IDENTITY;

/**
 * Created by Dmytro Honchar
 * Date: 7/12/2022
 */
@Entity
@Table(name = "singer")
@Data
public class Singer implements Serializable {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id")
    private Long id;

    @Version
    @Column(name = "version")
    private int version;

    @NotBlank(message = "{validation.firstname.NotBlank.message}") //@NotEmpty replaced
    @Size(min = 3, max = 60, message = "{validation.firstname.Size.message}")
    @Column(name = "first_name")
    private String firstName;

    @NotBlank(message = "{validation.lastname.NotBlank.message}") //@NotEmpty replaced
    @Size(min = 1, max = 40, message = "{validation.lastname.Size.message}")
    @Column(name = "last_name")
    private String lastName;

    @NotNull
    @Temporal(TemporalType.DATE)
    @Column(name = "birth_date")
    private Date birthDate;

    @Column(name = "description")
    private String description;

    @Basic(fetch = FetchType.LAZY)
    @Lob //big object(image)
    @Column(name = "photo")
    private byte photo;

    @Transient //????
    public String getBirthDateString() {
        String birthDateString = "";
        if (birthDate != null) {
            SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yyyy");
            birthDateString = sdf.format(birthDate);
        }
        return birthDateString;
    }
}