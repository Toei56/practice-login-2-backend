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
import java.time.Instant;

@Getter
@Setter
@NoArgsConstructor
@Accessors(chain = true)
@Entity
public class JwtToken extends BaseModel implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @OneToOne(mappedBy = "jwtToken")
    @JsonBackReference
    private User user;

    @Column(nullable = false)
    private String jwtToken;

    @Column(nullable = false)
    private Instant issuedAt;

    @Column(nullable = false)
    private Instant expiresAt;
}
