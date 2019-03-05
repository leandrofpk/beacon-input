package br.gov.inmetro.beacon.input.scheduling;

import br.gov.inmetro.beacon.input.BeaconInputApplication;
import br.gov.inmetro.beacon.input.application.RestApiRepo;
import br.gov.inmetro.beacon.input.infra.EmailAvisoService;
import br.gov.inmetro.beacon.input.randomness.entropy.Entropy;
import br.gov.inmetro.beacon.input.randomness.entropy.IEntropyService;
import br.gov.inmetro.beacon.input.exceptions.NoiseSourceReadError;
import br.gov.inmetro.beacon.input.randomness.noise.INoiseService;
import br.gov.inmetro.beacon.input.randomness.noise.NoiseDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Component
@EnableScheduling
public class EntropySourceScheduling {

    private final INoiseService noiseService;

    private final IEntropyService entropyService;

    private final EmailAvisoService mailService;

    private final RestApiRepo restApiRepo;

    private static final Logger logger = LoggerFactory.getLogger(BeaconInputApplication.class);

    @Autowired
    public EntropySourceScheduling(INoiseService noiseService, IEntropyService entropyService, EmailAvisoService mailService, RestApiRepo restApiRepo) {
        this.noiseService = noiseService;
        this.entropyService = entropyService;
        this.mailService = mailService;
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

        } catch (NoiseSourceReadError e) {
            e.printStackTrace();
            logger.error("Logger no scheduling:" + e.getMessage());
            enviarEmailSimples(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("Logger no scheduling 2:" + e.getMessage());
        }

        try {
            ResponseEntity<String> response = restApiRepo.send(noiseDto);
            if (HttpStatus.CREATED.equals(response.getStatusCode())){
                entropyService.sent(saved.getId());
            }
        } catch (Exception e){
            logger.error("Number not sent: " + saved.getTimeStamp());
        }

    }

    private void enviarEmailSimples(String erro){
        StringBuilder body = new StringBuilder();
        String subject = "Beacon Alarm - Health Test";
        mailService.sendSimpleMessage(subject, body);
    }

}