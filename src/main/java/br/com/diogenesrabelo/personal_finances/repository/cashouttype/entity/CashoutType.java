package br.com.diogenesrabelo.personal_finances.repository.cashouttype.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "cashout_types")
public class CashoutType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "type_id")
    private Long id;

    private String name;

    private String description;

    private boolean active;

    private String user;

    public CashoutType(){}

    public CashoutType buildCashoutType(String name, String description, boolean active, String user) {
        var cashoutType = new CashoutType();
        cashoutType.setName(name);
        cashoutType.setDescription(description);
        cashoutType.setActive(active);
        cashoutType.setUser(user);
        return cashoutType;
    }

    public CashoutType buildCashoutTypeAllArgs(Long id, String name, String description, boolean active, String user) {
        var cashoutType = new CashoutType();
        cashoutType.setId(id);
        cashoutType.setName(name);
        cashoutType.setDescription(description);
        cashoutType.setActive(active);
        cashoutType.setUser(user);
        return cashoutType;
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