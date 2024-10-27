package br.com.diogenesrabelo.personal_finances.repository.cashout.entity;

import br.com.diogenesrabelo.personal_finances.repository.cashouttype.entity.CashoutType;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "cashout")
public class Cashout {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        private LocalDate date;
        private BigDecimal value;
        private String description;

        @ManyToOne(fetch = FetchType.EAGER)
        @JoinColumn(name = "type_id")
        private CashoutType type;

        private String user;

        public Cashout(){}

        public Cashout buildCashout(LocalDate date, BigDecimal value, String description, CashoutType type, String user) {
                var cashout = new Cashout();
                cashout.setDate(date);
                cashout.setValue(value);
                cashout.setDescription(description);
                cashout.setType(type);
                cashout.setUser(user);
                return cashout;
        }


        public Cashout buildCashoutAllArgs(Long id, LocalDate date, BigDecimal value, String description, CashoutType type, String user) {
                var cashout = new Cashout();
                cashout.setId(id);
                cashout.setDate(date);
                cashout.setValue(value);
                cashout.setDescription(description);
                cashout.setType(type);
                cashout.setUser(user);
                return cashout;
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

        public CashoutType getType() {
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

        public void setType(CashoutType type) {
                this.type = type;
        }

        public void setUser(String user) {
                this.user = user;
        }
}