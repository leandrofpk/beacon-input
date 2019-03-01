package br.gov.inmetro.beacon.input.noise;

import br.gov.inmetro.beacon.input.BeaconInputApplication;
import br.gov.inmetro.beacon.input.application.RestApiRepo;
import br.gov.inmetro.beacon.input.entropy.Entropy;
import br.gov.inmetro.beacon.input.entropy.IEntropyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@EnableScheduling
public class SynchronizeRemoteNumbersScheduling {

    private final RestApiRepo restApiRepo;

    private final IEntropyService iEntropyService;

    private static final Logger logger = LoggerFactory.getLogger(BeaconInputApplication.class);

    @Autowired
    public SynchronizeRemoteNumbersScheduling(RestApiRepo restApiRepo, IEntropyService iEntropyService) {
        this.restApiRepo = restApiRepo;
        this.iEntropyService = iEntropyService;
    }

    @Scheduled(cron = "*/60 * * * * *")
    public void getNoise() {
        List<Entropy> notSent = iEntropyService.getNotSent();
        System.out.println(notSent.size());

        if (notSent.isEmpty()) return;

        try {
            for (Entropy e : notSent) {
                NoiseDto noiseDto = new NoiseDto(e.getTimeStamp(),
                        e.getRawData(), "chain 2", "60", "Version 1.0", "0");

                restApiRepo.send(noiseDto);
                iEntropyService.sent(e.getId());
            }
        } catch (Exception e){
//            logger.error("Number not sent: " + saved.getTimeStamp());
            System.out.println("TESTE");

        }

    }
}
