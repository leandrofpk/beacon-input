package br.gov.inmetro.beacon.input.application;

import br.gov.inmetro.beacon.input.randomness.entropy.EntropyDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Component
public class RestApiRepo {

    @Value("${beacon.api.url}")
    private String uri;

    @Autowired
    private RestTemplate restTemplate;

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder.basicAuthentication("beacon-2", "31a9e0bb-69de-49d0-98ea-bc375748bce3")
                .errorHandler(new RestTemplateResponseErrorHandler())
                .build();
    }


    public ResponseEntity<String> send(EntropyDto noiseDto) {
        return restTemplate.postForEntity(uri, noiseDto, String.class);
    }

    public ResponseEntity<String> send(List<EntropyDto> noises) {
//        return restTemplate.postForEntity(uri, noises, List<EntropyDto>.class);
        return restTemplate.postForObject(uri + "/sync", noises, ResponseEntity.class);
    }


}
