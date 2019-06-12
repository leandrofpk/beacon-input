package br.gov.inmetro.beacon.input.queue;

import br.gov.inmetro.beacon.input.randomness.entropy.Entropy;
import br.gov.inmetro.beacon.input.randomness.entropy.IEntropyRepository;
import br.gov.inmetro.beacon.input.randomness.noise.INoiseService;
import br.gov.inmetro.beacon.input.randomness.noise.NoiseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@EnableScheduling
public class QueueScheduling {

    private final BeaconPulseQueueSender beaconQueueSender;

    private final INoiseService noiseService;

    private final IEntropyRepository entropyRepository;

    @Autowired
    public QueueScheduling(BeaconPulseQueueSender orderQueueSender, INoiseService noiseService, IEntropyRepository entropyService) {
        this.beaconQueueSender = orderQueueSender;
        this.noiseService = noiseService;
        this.entropyRepository = entropyService;
    }

    @Scheduled(cron = "10 * * * * *")
    public void processar() throws Exception {
        NoiseDto noiseDto = noiseService.getNoise();
        Entropy saved = entropyRepository.save(noiseDto);

        try {
            beaconQueueSender.send(noiseDto);
        } catch (Exception e){
            entropyRepository.sent(saved.getId(), false);
            e.printStackTrace();
        }

    }

//    @Scheduled(cron = "15 * * * * *")
    public void testeUm() throws Exception {
        NoiseDto noiseDto = noiseService.getNoise("2");
        Entropy saved = entropyRepository.save(noiseDto);

        try {
            beaconQueueSender.send(noiseDto);
        } catch (Exception e){
            entropyRepository.sent(saved.getId(), false);
            e.printStackTrace();
        }

    }

}
