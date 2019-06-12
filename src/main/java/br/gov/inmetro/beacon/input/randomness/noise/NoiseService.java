package br.gov.inmetro.beacon.input.randomness.noise;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@Component
class NoiseService implements INoiseService {

    private final INoiseSource noiseSource;

    private final Environment env;


    @Autowired
    NoiseService(INoiseSource noiseSource, Environment env) {
        this.noiseSource = noiseSource;
        this.env = env;
    }

    public NoiseDto getNoise() throws Exception {
        final String noise512Bits = noiseSource.getNoise512Bits();

        NoiseDto noiseDto = new NoiseDto(LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES),
                noise512Bits, env.getProperty("beacon.entropy.chain"), "60", env.getProperty("beacon.noise-source"));

        return noiseDto;
    }

    public NoiseDto getNoise(String beaconNoiseSource) throws Exception {
        final String noise512Bits = noiseSource.getNoise512Bits();

        NoiseDto noiseDto = new NoiseDto(LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES),
                noise512Bits, env.getProperty("beacon.entropy.chain"), "60", beaconNoiseSource);

        return noiseDto;
    }

    public NoiseDto getNoise(String chain, String beaconNoiseSource) throws Exception {
        final String noise512Bits = noiseSource.getNoise512Bits();

        NoiseDto noiseDto = new NoiseDto(LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES),
                noise512Bits, chain, "60", beaconNoiseSource);

        return noiseDto;
    }

}
