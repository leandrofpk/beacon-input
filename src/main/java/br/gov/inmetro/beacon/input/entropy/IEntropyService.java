package br.gov.inmetro.beacon.input.entropy;

import br.gov.inmetro.beacon.input.noise.NoiseDto;

import java.util.List;

public interface IEntropyService {

    Entropy save(NoiseDto noiseDto);

    void sent(Long id);

    List<Entropy> getNotSent();

}
