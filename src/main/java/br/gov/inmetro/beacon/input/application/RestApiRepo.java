package br.gov.inmetro.beacon.input.application;

import br.gov.inmetro.beacon.input.noise.NoiseDto;
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

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder.basicAuthentication("beacon", "483588e8-c51e-47d3-84eb-0c30e822988e")
                .errorHandler(new RestTemplateResponseErrorHandler())
                .build();
    }

    @Autowired
    private RestTemplate restTemplate;


    public void send(NoiseDto noiseDto) {
        ResponseEntity<NoiseDto> response = restTemplate.postForEntity(uri, noiseDto, NoiseDto.class);
        System.out.println(response.getStatusCode());
    }


}
