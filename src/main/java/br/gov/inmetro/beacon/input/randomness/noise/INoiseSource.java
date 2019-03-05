package br.gov.inmetro.beacon.input.randomness.noise;

import br.gov.inmetro.beacon.input.exceptions.NoiseSourceReadError;

import java.io.IOException;

interface INoiseSource {
    String getNoise512Bits() throws IOException, InterruptedException, NoiseSourceReadError;
}
