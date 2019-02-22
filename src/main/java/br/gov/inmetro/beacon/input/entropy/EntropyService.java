package br.gov.inmetro.beacon.input.entropy;

import br.gov.inmetro.beacon.input.noise.NoiseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.ZoneId;

@Component
public class EntropyService implements IEntropyService{

    private Entropies entropies;

    @Autowired
    public EntropyService(Entropies entropies) {
        this.entropies = entropies;
    }

    @Override
    @Transactional
    public void save(NoiseDto noiseDto) {

        Entropy entropy = new Entropy();

        entropy.setChain("1");
        entropy.setRawData(noiseDto.getRawData());
        entropy.setFrequency("60");
        entropy.setOutputValue("RawData+:" + noiseDto.getRawData());
        entropy.setTimeStamp(noiseDto.getTimeStampDateTime());
        entropy.setVersionBeacon("1.0");
        entropy.setOrigin(OriginEnum.COMSCIRE_PQ32MS);

//        entropy.setUnixTimeStamp(new Long(String.valueOf(noiseDto.getTimeStamp().atZone(ZoneId.of("America/Sao_Paulo")).toInstant().toEpochMilli())));

        entropy.setUnixTimeStamp(entropy.getTimeStamp().atZone(ZoneId.of("America/Sao_Paulo")).toInstant().toEpochMilli());

//        this.timeStamp = String.valueOf(record.getTimeStamp().atZone(ZoneId.of("America/Sao_Paulo")).toInstant().toEpochMilli());


        entropies.save(entropy);
    }
}
