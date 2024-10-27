package br.com.diogenesrabelo.personal_finances.repository.cashintype.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "cashin_types")
public class CashinType {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "type_id")
    private Long id;

    private String name;

    private String description;

    private boolean active;

    private String user;

    public CashinType(){}

    public CashinType buildCashinType(String name, String description, boolean active, String user) {
        var cashinType = new CashinType();
        cashinType.setName(name);
        cashinType.setDescription(description);
        cashinType.setActive(active);
        cashinType.setUser(user);
        return cashinType;
    }


    public CashinType buildCashinTypeAllArgs(Long id, String name, String description, boolean active, String user) {
        var cashinType = new CashinType();
        cashinType.setId(id);
        cashinType.setName(name);
        cashinType.setDescription(description);
        cashinType.setActive(active);
        cashinType.setUser(user);
        return cashinType;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public boolean isActive() {
        return active;
    }

    public void disable() {
        active = false;
    }

    public String getUser() {
        return user;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public void setUser(String user) {
        this.user = user;
    }
}