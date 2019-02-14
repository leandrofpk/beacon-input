package br.gov.inmetro.beacon.input;

import br.gov.inmetro.beacon.input.repository.Noises;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@EnableScheduling
public class EntropySourceScheduling {


    private final Noises noises;

    @Autowired
    public EntropySourceScheduling(Noises noises) {
        this.noises = noises;
    }

    @Scheduled(cron = "*/10 * * * * *")
    public void getNoise() throws IOException, InterruptedException {
        System.out.println(noises.getNoise());
    }

}
