package br.gov.inmetro.beacon.input.queue;

import br.gov.inmetro.beacon.input.randomness.entropy.Entropy;
import br.gov.inmetro.beacon.input.randomness.entropy.EntropyDto;
import br.gov.inmetro.beacon.input.randomness.entropy.IEntropyRepository;
import br.gov.inmetro.beacon.input.randomness.noise.INoiseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@EnableScheduling
public class QueueScheduling {

    private final BeaconPulseQueueSender beaconQueueSender;

    private final INoiseService noiseService;

    private final IEntropyRepository entropyRepository;

    private static final Logger logger = LoggerFactory.getLogger(QueueScheduling.class);

    @Autowired
    public QueueScheduling(BeaconPulseQueueSender orderQueueSender, INoiseService noiseService, IEntropyRepository entropyService) {
        this.beaconQueueSender = orderQueueSender;
        this.noiseService = noiseService;
        this.entropyRepository = entropyService;
    }

    @Scheduled(cron = "50 * * * * *")
    public void runRegular() throws Exception {
        EntropyDto noiseDto = noiseService.getNoise();
        Entropy saved = entropyRepository.save(noiseDto);

        try {
            beaconQueueSender.sendRegular(noiseDto);
            logger.warn(noiseDto.toString());

        } catch (Exception e){
            entropyRepository.sent(saved.getId(), false);
            e.printStackTrace();
        }

    }

    @Scheduled(cron = "51 * * * * *")
    public void runSync() {
        List<EntropyDto> notSentDto = entropyRepository.getNotSentDto();

        if (notSentDto.isEmpty()) return;

        try {
            beaconQueueSender.sendSync(notSentDto);
            entropyRepository.sentDto(notSentDto);
        } catch (Exception e){
            e.printStackTrace();
        }

    }

}
