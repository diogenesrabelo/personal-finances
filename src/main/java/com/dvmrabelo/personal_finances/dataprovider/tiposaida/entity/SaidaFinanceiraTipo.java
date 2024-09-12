package com.dvmrabelo.personal_finances.dataprovider.tiposaida.entity;

import com.dvmrabelo.personal_finances.dataprovider.user.entity.UserEntity;
import jakarta.persistence.*;

@Entity
@Table(name = "saida_financeira_tipo")
public class SaidaFinanceiraTipo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    private String descricao;

    private boolean ativo;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity createdBy;

    public SaidaFinanceiraTipo(Long id, String nome, String descricao, boolean ativo) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.ativo = ativo;
    }

    public Long id() {
        return id;
    }

    public String nome() {
        return nome;
    }

    public String descricao() {
        return descricao;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public UserEntity getCreatedBy() {
        return createdBy;
    }

    public void setDados(SaidaFinanceiraTipo newSaidaFinanceiraTipo) {
        this.nome = newSaidaFinanceiraTipo.nome;
        this.descricao = newSaidaFinanceiraTipo.descricao;
        this.ativo = newSaidaFinanceiraTipo.ativo;
    }
}