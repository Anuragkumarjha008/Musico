package com.musico.user.entity;

import com.musico.common.audit.AuditableEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(
        name = "users",
        schema = "users"
)
public class User extends AuditableEntity {

    @Column(
            name = "username",
            nullable = false,
            unique = true,
            length = 50
    )
    private String username;

    @Column(
            name = "email",
            nullable = false,
            unique = true,
            length = 255
    )
    private String email;

    @Column(
            name = "password",
            nullable = false,
            length = 255
    )
    private String password;

    @Column(
            name = "display_name",
            nullable = false,
            length = 100
    )
    private String displayName;

    @Column(
            name = "bio",
            columnDefinition = "TEXT"
    )
    private String bio;

    @Column(
            name = "profile_picture",
            length = 500
    )
    private String profilePicture;

    @Enumerated(EnumType.STRING)
    @Column(
            name = "status",
            nullable = false,
            length = 20
    )
    private UserStatus status;

    @Enumerated(EnumType.STRING)
    @Column(
            name = "role",
            nullable = false,
            length = 20
    )
    private UserRole role;
}