package br.gov.inmetro.beacon.input.randomness.noise;

import br.gov.inmetro.beacon.input.exceptions.NoiseSourceReadError;

import java.io.IOException;

public interface INoiseService {
    String get512Bits() throws IOException, InterruptedException, NoiseSourceReadError;
}
