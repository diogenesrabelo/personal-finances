package br.com.diogenesrabelo.personal_finances.service.ollama;

import br.com.diogenesrabelo.personal_finances.client.dto.IaRequest;
import br.com.diogenesrabelo.personal_finances.client.dto.IaResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class OllamaService {

    private final WebClient webClient;

    public OllamaService(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl("http://localhost:11434").build();
    }

    public Mono<String> consumeAndSaveStream(IaRequest request) {
        return this.webClient.post()
            .uri("/api/generate")
            .body(Mono.just(request), IaRequest.class)
            .retrieve()
            .bodyToFlux(IaResponse.class)
            .map(IaResponse::response)
            .reduce("", (accumulated, current) -> accumulated + current);
    }
}
