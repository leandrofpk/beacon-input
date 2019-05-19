package br.gov.inmetro.beacon.input.randomness.noise;

import br.gov.inmetro.beacon.input.exceptions.NoiseSourceReadError;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

interface INoiseSource {
    String getNoise512Bits() throws IOException, InterruptedException, NoiseSourceReadError, NoSuchAlgorithmException, Exception;
}
