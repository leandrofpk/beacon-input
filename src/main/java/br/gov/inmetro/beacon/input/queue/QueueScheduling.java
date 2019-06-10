package br.gov.inmetro.beacon.input.queue;

import br.gov.inmetro.beacon.input.randomness.entropy.Entropy;
import br.gov.inmetro.beacon.input.randomness.entropy.IEntropyService;
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

    private final IEntropyService entropyService;

    @Autowired
    public QueueScheduling(BeaconPulseQueueSender orderQueueSender, INoiseService noiseService, IEntropyService entropyService) {
        this.beaconQueueSender = orderQueueSender;
        this.noiseService = noiseService;
        this.entropyService = entropyService;
    }

    @Scheduled(cron = "10 * * * * *")
    public void teste() throws Exception {
//        Entropy saved = null;
        NoiseDto noiseDto = noiseService.getNoise();
//        entropyService.save(noiseDto);
        beaconQueueSender.send(noiseDto);
        System.out.println("Sending...");
        System.out.println(noiseDto);
    }

    @Scheduled(cron = "15 * * * * *")
    public void testeDois() throws Exception {
//        Entropy saved = null;
        NoiseDto noiseDto = noiseService.getNoise("2");
//        entropyService.save(noiseDto);
        beaconQueueSender.send(noiseDto);
        System.out.println("Sending...");
        System.out.println(noiseDto);
    }

}
