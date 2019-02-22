package br.gov.inmetro.beacon.input.noise;

import br.gov.inmetro.beacon.input.BeaconInputApplication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
class NoiseService implements INoiseService {

    private final INoiseSource noiseSource;

    private static final Logger logger = LoggerFactory.getLogger(BeaconInputApplication.class);

    @Autowired
    NoiseService(INoiseSource noiseSource) {
        this.noiseSource = noiseSource;
    }

    public String get512Bits(){
        String noise = null;
        try {
            noise = noiseSource.getNoise512Bits();



        } catch (Exception e) {
            logger.error(e.getMessage());
            e.printStackTrace();
            throw new NoiseException(e.getMessage());
        }
        return noise;
    }

}
