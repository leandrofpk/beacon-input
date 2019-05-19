package br.gov.inmetro.beacon.input.randomness.noise;

import br.gov.inmetro.beacon.input.BeaconInputApplication;
import br.gov.inmetro.beacon.input.exceptions.NoiseSourceReadError;
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

    public String get512Bits() throws Exception, InterruptedException, NoiseSourceReadError {
        return noiseSource.getNoise512Bits();
    }

}
