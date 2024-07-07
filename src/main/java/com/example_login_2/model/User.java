package com.example_login_2.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Accessors(chain = true)
@Entity
@Table(name = "`user`")
public class User extends BaseModel {

    @Column(nullable = false, unique = true, length = 60)
    private String email;

    @Column(nullable = false, length = 120)
    private String password;

    @Column(length = 60)
    private String firstName;

    @Column(length = 60)
    private String lastName;

    @Column(length = 10)
    private String phoneNumber;

    @Column
    private Date dateOfBirth;

    @Column
    private String gender;

    @Column
    private String profilePicture;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"))
    @Column
    private Set<String> roles = new HashSet<>();

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "address_id", referencedColumnName = "id")
    @JsonManagedReference
    private Address address;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "email_confirm_id", referencedColumnName = "id")
    @JsonManagedReference
    private EmailConfirm emailConfirm;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private Set<JwtToken> jwtTokens = new HashSet<>();

    @PreRemove
    private void preRemove() {
        this.roles.clear();
    }

    public void addJwtToken(JwtToken jwtToken) { //ถ้าใช้ setJwtToken จะเป็นการแทนที่ค่าเดิม เลยต้องใช้ addJwtToken แทน
        jwtToken.setUser(this);
        this.jwtTokens.add(jwtToken);
    }

    public void revokeAllJwtToken() {
        for (JwtToken jwtToken : this.jwtTokens) {
            jwtToken.setRevoked(true);
        }
    }
}
