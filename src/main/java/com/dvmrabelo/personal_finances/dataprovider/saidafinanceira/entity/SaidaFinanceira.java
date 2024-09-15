package com.dvmrabelo.personal_finances.dataprovider.saidafinanceira.entity;

import com.dvmrabelo.personal_finances.dataprovider.tiposaida.entity.TipoSaidaFinanceira;
import com.dvmrabelo.personal_finances.dataprovider.user.entity.UserEntity;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "saida_financeira")
public class SaidaFinanceira {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        private LocalDate data;
        private BigDecimal valor;
        private String descricao;

        @ManyToOne
        @JoinColumn(name = "tipo_id")
        private TipoSaidaFinanceira tipo;

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

        public String getDescricao() {
                return descricao;
        }

        public TipoSaidaFinanceira getTipo() {
                return tipo;
        }

        public UserEntity getCreatedBy() {
                return createdBy;
        }

        public SaidaFinanceira(LocalDate data, BigDecimal valor, TipoSaidaFinanceira tipo, UserEntity createdBy, String descricao) {
                this.data = data;
                this.valor = valor;
                this.tipo = tipo;
                this.createdBy = createdBy;
                this.descricao = descricao;
        }

        public void setDados(SaidaFinanceira saida) {
                this.data = saida.getData();
                this.valor = saida.getValor();
                this.tipo = saida.getTipo();
                this.descricao = saida.getDescricao();
        }
}