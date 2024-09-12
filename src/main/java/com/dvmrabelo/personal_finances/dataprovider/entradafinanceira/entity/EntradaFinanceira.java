package com.dvmrabelo.personal_finances.dataprovider.entradafinanceira.entity;

import com.dvmrabelo.personal_finances.dataprovider.tipoentrada.entity.EntradaFinanceiraTipo;
import com.dvmrabelo.personal_finances.dataprovider.user.entity.UserEntity;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "entrada_financeira")
public class EntradaFinanceira {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate data;

    private BigDecimal valor;

    private String descricao;

    @ManyToOne
    @JoinColumn(name = "tipo_id")
    private EntradaFinanceiraTipo tipo;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity createdBy;

    public Long getId() {
        return id;
    }

    public LocalDate getData() {
        return data;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public EntradaFinanceiraTipo getTipo() {
        return tipo;
    }

    public UserEntity getCreatedBy() {
        return createdBy;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDados(EntradaFinanceira newEntradaFinanceira) {
        this.data = newEntradaFinanceira.getData();
        this.valor = newEntradaFinanceira.getValor();
        this.tipo = newEntradaFinanceira.getTipo();
        this.descricao = newEntradaFinanceira.getDescricao();
    }
}