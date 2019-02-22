package br.gov.inmetro.beacon.input.entropy;

import br.gov.inmetro.beacon.input.noise.NoiseDto;

public interface IEntropyService {

    void save(NoiseDto noiseDto);
}
