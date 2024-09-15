package com.dvmrabelo.personal_finances.dataprovider.user.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "roles")
public class RoleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    public RoleEntity(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
