package br.gov.inmetro.beacon.input.randomness.entropy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Collections;
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
    public Entropy save(EntropyDto dto) {

        Entropy entropy = new Entropy();

        entropy.setChain(dto.getChain());
        entropy.setRawData(dto.getRawData());
        entropy.setFrequency("60");
        entropy.setTimeStamp(dto.getTimeStampDateTime());
        entropy.setVersionBeacon(env.getProperty("beacon.version"));
        entropy.setOrigin(OriginEnum.COMSCIRE_PQ32MS);
        entropy.setUnixTimeStamp(entropy.getTimeStamp().atZone(ZoneId.of("America/Sao_Paulo")).toInstant().toEpochMilli());
        entropy.setNoiseSource(dto.getNoiseSource());

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

    @Transactional
    public void sentDto(List<EntropyDto> notSent){
        notSent.forEach(entropy -> entropies.findById(entropy.getId()).get().setSent(true));
    }

    @Transactional(readOnly = true)
    public List<Entropy> getNotSent(){
        return entropies.findBySentOrderById(false);
    }

    @Transactional(readOnly = true)
    public List<EntropyDto> getNotSentDto(){
        List<EntropyDto> list = new ArrayList<>();
        entropies.findBySentOrderById(false).forEach(entropy -> list.add(new EntropyDto(entropy)));
        return Collections.unmodifiableList(list);
    }



}
