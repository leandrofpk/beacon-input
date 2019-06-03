package br.gov.inmetro.beacon.input.randomness.entropy;

import br.gov.inmetro.beacon.input.randomness.noise.NoiseDto;

import java.util.List;

public interface IEntropyService {

    Entropy save(NoiseDto noiseDto);

    void sent(Long id);

    void sent(List<Entropy> notSent);

    List<Entropy> getNotSent();

}
