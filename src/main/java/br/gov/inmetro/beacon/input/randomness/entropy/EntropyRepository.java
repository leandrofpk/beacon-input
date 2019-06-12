package br.gov.inmetro.beacon.input.randomness.entropy;

import br.gov.inmetro.beacon.input.randomness.noise.NoiseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.ZoneId;
import java.util.List;

@Component
public class EntropyRepository implements IEntropyRepository {

    private Entropies entropies;

    private Environment env;

    @Autowired
    public EntropyRepository(Entropies entropies, Environment env) {
        this.entropies = entropies;
        this.env = env;
    }

    @Override
    @Transactional
    public Entropy save(NoiseDto noiseDto) {

        Entropy entropy = new Entropy();

        entropy.setChain(noiseDto.getChain());
        entropy.setRawData(noiseDto.getRawData());
        entropy.setFrequency("60");
        entropy.setTimeStamp(noiseDto.getTimeStampDateTime());
        entropy.setVersionBeacon(env.getProperty("beacon.version"));
        entropy.setOrigin(OriginEnum.COMSCIRE_PQ32MS);
        entropy.setUnixTimeStamp(entropy.getTimeStamp().atZone(ZoneId.of("America/Sao_Paulo")).toInstant().toEpochMilli());

        return entropies.save(entropy);
    }

    @Transactional
    public void sent(Long id, boolean value){
        entropies.save(entropies.findById(id).get().sentRemote(value));
    }

    @Transactional
    public void sent(List<Entropy> notSent){
        notSent.forEach(entropy -> entropy.setSent(true));
        entropies.saveAll(notSent);
    }

    @Transactional(readOnly = true)
    public List<Entropy> getNotSent(){
        return entropies.findBySentOrderById(false);
    }

}
