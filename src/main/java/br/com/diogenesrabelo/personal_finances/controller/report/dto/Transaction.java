package br.com.diogenesrabelo.personal_finances.controller.report.dto;

import br.com.diogenesrabelo.personal_finances.repository.cashin.entity.Cashin;
import br.com.diogenesrabelo.personal_finances.repository.cashout.entity.Cashout;

import java.math.BigDecimal;
import java.time.LocalDate;

public record Transaction(
        Long id,
        String name,
        String description,
        BigDecimal value,
        LocalDate date,
        String month
) {
    public static Transaction convertToTransactionCashin(Cashin cashin) {
        return new Transaction(
                cashin.getId(),
                cashin.getType().getName(),
                cashin.getDescription(),
                cashin.getValue(),
                cashin.getDate(),
                cashin.getDate().getMonth().name()
        );
    }

    public static Transaction convertToTransactionCashout(Cashout cashout) {
        return new Transaction(
                cashout.getId(),
                cashout.getType().getName(),
                cashout.getDescription(),
                cashout.getValue(),
                cashout.getDate(),
                cashout.getDate().getMonth().name()
        );
    }

    @Override
    public String toString() {
        return "Transaction{" +
            "id=" + id +
            ", name='" + name + '\'' +
            ", description='" + description + '\'' +
            ", value=" + value +
            ", date=" + date +
            ", month='" + month + '\'' +
            '}';
    }
}
