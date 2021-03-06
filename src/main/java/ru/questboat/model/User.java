package ru.questboat.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;

/**
 * Created by Mikhail Falaleev on 25.03.2017.
 */

@Entity(name = "USERS")
public @Data class User {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_seq")
    @SequenceGenerator(name = "user_seq", sequenceName = "user_seq", allocationSize = 1)
    @JsonView(View.Base.class)
    private Long id;

    @Column(name = "USERNAME", length = 50, unique = true)
    @NotNull
    @Size(min = 2, max = 50)
    @JsonView(View.Base.class)
    private String username;

    @Column(name = "PASSWORD", length = 100)
    @NotNull
    @Size(min = 4, max = 100)
//    @JsonIgnore
    private String password;

    @Column(name = "FIRSTNAME", length = 50)
    @NotNull
    @Size(min = 2, max = 50)
    @JsonView(View.Summary.class)
    private String firstName;

    @Column(name = "LASTNAME", length = 50)
    @NotNull
    @Size(min = 2, max = 50)
    @JsonView(View.Summary.class)
    private String lastName;

    @Column(name = "EMAIL", length = 50)
    @NotNull
    @Size(min = 4, max = 50)
    @JsonView(View.Summary.class)
    private String email;

    @Column(name = "ENABLED")
    @NotNull
    private Boolean enabled;

    @Column(name = "LAST_PASSWORD_RESET_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    @NotNull
    private Date lastPasswordResetDate;

    @Column(name = "IMAGE_PATH")
    @NotNull
    @JsonView(View.Summary.class)
    private String imagePath;

    @Column(name = "BIRTHDAY")
    @Temporal(TemporalType.TIMESTAMP)
    @NotNull
    @JsonView(View.Summary.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd.MM.yyyy")
    private Date birthday;

    @Column(name = "CITY")
    @NotNull
    @JsonView(View.Summary.class)
    private String city;



    @ManyToMany(fetch = FetchType.EAGER)
    @JsonIgnore
    @JoinTable(
            name = "USER_AUTHORITY",
            joinColumns = {@JoinColumn(name = "USER_ID", referencedColumnName = "ID")},
            inverseJoinColumns = {@JoinColumn(name = "AUTHORITY_ID", referencedColumnName = "ID")})
    private List<Authority> authorities;

}
