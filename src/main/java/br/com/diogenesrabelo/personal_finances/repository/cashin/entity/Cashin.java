package br.com.diogenesrabelo.personal_finances.repository.cashin.entity;

import br.com.diogenesrabelo.personal_finances.repository.cashintype.entity.CashinType;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "cashin")
public class Cashin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate date;

    private BigDecimal value;

    private String description;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "type_id")
    private CashinType type;

    private String user;

    public Cashin(){
    }

    public Cashin buildCashin(LocalDate date, BigDecimal value, String description, CashinType type, String user) {
        var cashin = new Cashin();
        cashin.setId(id);
        cashin.setDate(date);
        cashin.setValue(value);
        cashin.setDescription(description);
        cashin.setType(type);
        cashin.setUser(user);
        return cashin;
    }

    public Cashin buildCashinAllArgs(Long id, LocalDate date, BigDecimal value, String description, CashinType type, String user) {
        var cashin = new Cashin();
        cashin.setId(id);
        cashin.setDate(date);
        cashin.setValue(value);
        cashin.setDescription(description);
        cashin.setType(type);
        cashin.setUser(user);
        return cashin;
    }

    public Long getId() {
        return id;
    }

    public LocalDate getDate() {
        return date;
    }

    public BigDecimal getValue() {
        return value;
    }

    public String getDescription() {
        return description;
    }

    public CashinType getType() {
        return type;
    }

    public String getUser() {
        return user;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setType(CashinType type) {
        this.type = type;
    }

    public void setUser(String user) {
        this.user = user;
    }
}