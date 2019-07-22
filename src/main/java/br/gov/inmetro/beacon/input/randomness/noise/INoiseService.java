package br.gov.inmetro.beacon.input.randomness.noise;

import br.gov.inmetro.beacon.input.randomness.entropy.EntropyDto;

public interface INoiseService {
    EntropyDto getNoise() throws Exception;
}
