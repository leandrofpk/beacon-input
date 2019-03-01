package br.gov.inmetro.beacon.input;

import br.gov.inmetro.beacon.input.application.RestApiRepo;
import br.gov.inmetro.beacon.input.entropy.Entropy;
import br.gov.inmetro.beacon.input.entropy.IEntropyService;
import br.gov.inmetro.beacon.input.noise.DeviceException;
import br.gov.inmetro.beacon.input.noise.INoiseService;
import br.gov.inmetro.beacon.input.noise.NoiseDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private static final Logger logger = LoggerFactory.getLogger(BeaconInputApplication.class);

    @Autowired
    public EntropySourceScheduling(INoiseService noiseService, IEntropyService entropyService, RestApiRepo restApiRepo) {
        this.noiseService = noiseService;
        this.entropyService = entropyService;
        this.restApiRepo = restApiRepo;
    }

    @Scheduled(cron = "*/60 * * * * *")
    public void getNoise() {

        final String bytes;
        NoiseDto noiseDto = null;
        Entropy saved = null;

        try {
            bytes = noiseService.get512Bits();

            noiseDto = new NoiseDto(LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES),
                    bytes, "chain 2", "60", "Version 1.0", "0");

            saved = entropyService.save(noiseDto);
            restApiRepo.send(noiseDto);

        } catch (DeviceException e) {
            e.printStackTrace();
            logger.error("Logger no scheduling:" + e.getMessage());

        } catch (Exception e) {
            e.printStackTrace();
            logger.error("Logger no scheduling 2:" + e.getMessage());
        }

        try {
            restApiRepo.send(noiseDto);
            entropyService.sent(saved.getId());
        } catch (Exception e){
            logger.error("Number not sent: " + saved.getTimeStamp());
        }

    }
}
