package br.com.diogenesrabelo.personal_finances.service.goal.impl;

import br.com.diogenesrabelo.personal_finances.client.dto.IaRequest;
import br.com.diogenesrabelo.personal_finances.controller.goal.dto.GoalResponse;
import br.com.diogenesrabelo.personal_finances.controller.goal.dto.GoalsRequest;
import br.com.diogenesrabelo.personal_finances.controller.report.dto.GeneralBalanceResponse;
import br.com.diogenesrabelo.personal_finances.repository.goal.GoalRepository;
import br.com.diogenesrabelo.personal_finances.repository.goal.entity.Goal;
import br.com.diogenesrabelo.personal_finances.service.goal.GoalService;
import br.com.diogenesrabelo.personal_finances.service.ollama.OllamaService;
import br.com.diogenesrabelo.personal_finances.service.report.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class GoalServiceImpl implements GoalService {

    @Autowired
    private OllamaService ollamaService;

    @Autowired
    private ReportService reportService;

    @Autowired
    private GoalRepository goalRepository;

    public GoalResponse getResponse(String user) throws ChangeSetPersister.NotFoundException {
        var goal = goalRepository.findByUser(user);
        if (goal.isPresent())
            return new GoalResponse(goal.get().getObjectives(), goal.get().getResponse());
        else
            throw new ChangeSetPersister.NotFoundException();
    }

    @Override
    @Async
    public void processGoals(GoalsRequest goals, String user) {
        var balances = getReportLast3Month(user);
        var prompt = buildPrompt(goals.objectives(), balances);
        var iaResponse = ollamaService.consumeAndSaveStream(new IaRequest("llama3.2", prompt, false));

        goalRepository.deleteAllByUser(user);
        if (iaResponse != null)
            goalRepository.save(new Goal().buildGoal(goals.objectives(), iaResponse.block(), user));
        else {
            throw new RuntimeException("Erro ao criar objetivos");
        }
    }

    private List<GeneralBalanceResponse> getReportLast3Month(String user) {
        var dateNow = LocalDate.now();
        var year = dateNow.getYear();
        var lastMonth = dateNow.getMonth();
        List<GeneralBalanceResponse> listBalance = new ArrayList<>();
        if (lastMonth.getValue() == 1) {
            listBalance.add(reportService.getBalanceMonthly(year, lastMonth.getValue(), user));
            year = year - 1;
            listBalance.addAll(List.of(reportService.getBalanceMonthly(year, lastMonth.minus(1).getValue(), user),
                reportService.getBalanceMonthly(year, lastMonth.minus(2).getValue(), user)));
        }
        else if (lastMonth.getValue() == 2) {
            listBalance.addAll(List.of(reportService.getBalanceMonthly(year, lastMonth.minus(1).getValue(), user),
                reportService.getBalanceMonthly(year, lastMonth.minus(2).getValue(), user)));
            year = year - 1;
            listBalance.add(reportService.getBalanceMonthly(year, lastMonth.getValue(), user));
        } else {
            listBalance.addAll(List.of(reportService.getBalanceMonthly(year, lastMonth.minus(1).getValue(), user),
                reportService.getBalanceMonthly(year, lastMonth.minus(2).getValue(), user),
                reportService.getBalanceMonthly(year, lastMonth.minus(3).getValue(), user)));
        }
        return listBalance;
    }

    private String buildPrompt(String objective, List<GeneralBalanceResponse> balances) {


        String balancesString = balances.stream().map(Objects::toString).collect(Collectors.joining(", "));

        if (!balances.isEmpty())
            return "Irei te informar alguns objetivos financeiros e também os últimos 3 meses das minhas finanças," +
                " entradas e saídas financeiras, quero que você analise minhas finanças e veja o que eu posso" +
                " economizar, começando por grandes gastos e indo para os menores, focando somente nos gastos que" +
                " não são essenciais. Diga como eu posso fazer para alcançar meus objetivos financeiros, se o" +
                " objetivo for de curto prazo, limite-se a oferecer poupança e CDBs e tesouro direto próximo ao prazo de vencimento," +
                " se for de médio prazo ofereça tesouro direto de prazo semelhante ao objetivo, agora se for de longo prazo" +
                " ofereça uma carteira mais diversificada, com ações, fiis e tesouro direto de longíssimo prazo. não ofereça" +
                " produtos específicos ou lugares específicos para investir, informe também se aquele objetivo é possível," +
                " utilize a taxa de juros de 1% ao mês para tudo que for poupado ou investido. " +
                " Segue o objetivo: " + objective +
                " Agora irei te informar o balanço dos últimos 3 mês em formato json." + balancesString +
                " Considerações finais, coloque cada objetivo e sua resolução em um paráfrago e ao final coloque a frase:" +
                " Isso não é uma recomendação de compra ou vendo de ações.";
        else {
            return "Não há informações suficientes para fazer uma analise inteligente!";
        }
    }
}
