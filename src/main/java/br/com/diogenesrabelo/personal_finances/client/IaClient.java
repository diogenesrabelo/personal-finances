package br.com.diogenesrabelo.personal_finances.client;

import br.com.diogenesrabelo.personal_finances.client.dto.IaRequest;
import br.com.diogenesrabelo.personal_finances.client.dto.IaResponse;
import br.com.diogenesrabelo.personal_finances.config.FeignConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(value = "llama", url = "http://localhost:11434", configuration = FeignConfig.class)
public interface IaClient {

    @PostMapping("/api/generate")
    ResponseEntity<IaResponse> generate(@RequestBody IaRequest iaRequest);
}
