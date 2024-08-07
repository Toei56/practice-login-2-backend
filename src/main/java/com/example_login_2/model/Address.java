package com.example_login_2.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.io.Serial;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@Accessors(chain = true)
@Entity
public class Address extends BaseModel implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @OneToOne(mappedBy = "address")
    @JsonBackReference
    private User user;

    @Column(length = 120)
    private String address;

    @Column(length = 60)
    private String city;

    @Column(length = 60)
    private String stateProvince;

    @Column(length = 5)
    private String postalCode;

    @Column(length = 60)
    private String country;
}
