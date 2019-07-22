package br.gov.inmetro.beacon.input.randomness.noise;

import br.gov.inmetro.beacon.input.randomness.entropy.EntropyDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.time.ZoneOffset;
import java.time.ZonedDateTime;
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

    public EntropyDto getNoise() throws Exception {
        final String noise512Bits = noiseSource.getNoise512Bits();

        EntropyDto noiseDto = new EntropyDto(getDateTime(),
                noise512Bits, env.getProperty("beacon.entropy.chain"),
                Integer.parseInt(env.getProperty("beacon.period")),
                Byte.parseByte(env.getProperty("beacon.noise-source")));

        return noiseDto;
    }

    private ZonedDateTime getDateTime(){
        ZonedDateTime now = ZonedDateTime.now()
                .truncatedTo(ChronoUnit.MINUTES)
                .plus(1, ChronoUnit.MINUTES)
                .withZoneSameInstant((ZoneOffset.UTC).normalized());

        return now;
    }

}
