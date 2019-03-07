package br.gov.inmetro.beacon.input.application;

import br.gov.inmetro.beacon.input.randomness.noise.NoiseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

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


    public ResponseEntity<String> send(NoiseDto noiseDto) {
        return restTemplate.postForEntity(uri, noiseDto, String.class);
    }


}
