package com.dvmrabelo.personal_finances.dataprovider.entradafinanceira.entity;

import com.dvmrabelo.personal_finances.dataprovider.tipoentrada.entity.TipoEntradaFinanceira;
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
    private TipoEntradaFinanceira tipo;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity createdBy;

    public EntradaFinanceira(LocalDate data, BigDecimal valor, TipoEntradaFinanceira tipo, UserEntity createdBy, String descricao) {
        this.data = data;
        this.valor = valor;
        this.tipo = tipo;
        this.createdBy = createdBy;
        this.descricao = descricao;
    }

    public EntradaFinanceira(Long id, LocalDate data, BigDecimal valor, TipoEntradaFinanceira tipo, UserEntity createdBy, String descricao) {
        this.id = id;
        this.data = data;
        this.valor = valor;
        this.tipo = tipo;
        this.createdBy = createdBy;
        this.descricao = descricao;
    }
    public Long getId() {
        return id;
    }

    public LocalDate getData() {
        return data;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public TipoEntradaFinanceira getTipo() {
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