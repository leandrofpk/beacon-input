package br.gov.inmetro.beacon.input.application;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class MyService {

//    @Autowired
//    private RestTemplate restTemplate;

//    @Bean
//    public RestTemplate restTemplate(RestTemplateBuilder builder) {
//        return builder.basicAuthentication("beacon", "483588e8-c51e-47d3-84eb-0c30e822988e").build();
//    }

//    public Entropy someRestCall(String name) {
//        return this.restTemplate.getForObject("http://localhost:8080/rest/record", Entropy.class);
////        return this.restTemplate.getForObject("/{name}/details", Entropy.class, name);
//    }

}
