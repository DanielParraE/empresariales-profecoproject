package com.profeco.consumer.entities;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "consumers")
@Data @AllArgsConstructor @NoArgsConstructor @Builder
public class Consumer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "full_name", length = 50, nullable = false)
    @NotEmpty(message = "Name must not be empty!!")
    @Size(min = 1, max = 50, message = "0 < name.size < 50")
    private String fullName;

    private String surname;

    @Column(name = "rfc", unique = true, nullable = true, length = 13)
    //@NotEmpty(message = "RFC must not be empty!!")
    @Pattern(regexp = "^([A-ZÑ&]{3,4})(\\d{2}(?:0[1-9]|1[0-2])(?:0[1-9]|[12]\\d|3[01]))([A-Z\\d]{2})([A\\d])$", message = "RFC Pattern is wrong")
    private String rfc;

    @Column(name = "phone_number", length = 10)
    private String phoneNumber;

    @Column(name = "email", length = 50, nullable = false)
    @NotEmpty(message = "Email must not be empty!!")
    @Email(message = "Email must be valid")
    private String email;

    @Valid
    @JsonIgnore
    //@JsonManagedReference(value = "consumer-review")
    @OneToMany(mappedBy = "consumer", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Review> reviewList;

    @Valid
    @JsonIgnore
    //@JsonManagedReference(value = "consumer-wishlist")
    @OneToMany(mappedBy = "creator", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Wishlist> wishlists;

    @Valid
    @JsonManagedReference(value = "consumer-author")
    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Inconsistency> inconsistencies;

    private String image;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at")
    private Date createdAt;
    private String status;
}
