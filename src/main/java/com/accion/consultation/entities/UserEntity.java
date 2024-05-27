package com.accion.consultation.entities;

import com.accion.consultation.models.AdministrativeSex;
import com.accion.consultation.models.MaritalStatus;
import com.accion.consultation.models.UserStatus;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "user")
@Inheritance(strategy = InheritanceType.JOINED)
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @Column(unique = true, nullable = false)
    private String username;

    @JsonIgnore
    @Column(nullable = false)
    private String password;

    @Embedded
    private PersonName name;

    @Column(nullable = false)
    private String email;

    @Column
    private Instant dob;

    @Column
    private MaritalStatus maritalStatus;

    @Column(nullable = false)
    private AdministrativeSex administrativeSex;

    @Column(nullable = false)
    private UserStatus status;

    @Column(updatable = false)
    @CreationTimestamp
    private Instant createdAt;

    @UpdateTimestamp
    private Instant updatedAt;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    private List<RoleEntity> roles = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<UserAddressEntity> addresses = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<UserPhoneEntity> contacts = new ArrayList<>();
}
