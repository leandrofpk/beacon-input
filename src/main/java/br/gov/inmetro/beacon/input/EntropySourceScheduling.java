package br.gov.inmetro.beacon.input;

import br.gov.inmetro.beacon.input.application.RestApiRepo;
import br.gov.inmetro.beacon.input.entropy.IEntropyService;
import br.gov.inmetro.beacon.input.noise.INoiseService;
import br.gov.inmetro.beacon.input.noise.NoiseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@Component
@EnableScheduling
public class EntropySourceScheduling {

    private final INoiseService noiseService;

    private final IEntropyService entropyService;

    private final RestApiRepo restApiRepo;

    @Autowired
    public EntropySourceScheduling(INoiseService noiseService, IEntropyService entropyService, RestApiRepo restApiRepo) {
        this.noiseService = noiseService;
        this.entropyService = entropyService;
        this.restApiRepo = restApiRepo;
    }

//    @Scheduled(cron = "*/10 * * * * *")
    @Scheduled(cron = "*/15 * * * * *")
    public void getNoise() {
        System.out.println(LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES).toString());
        NoiseDto noiseDto = new NoiseDto(LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES),
                noiseService.get512Bits(), "chain 1", "60", "Version 1.0", "0");

        entropyService.save(noiseDto);
        // send to queue
        restApiRepo.send(noiseDto);


        System.out.println(noiseService.get512Bits());
    }

}
