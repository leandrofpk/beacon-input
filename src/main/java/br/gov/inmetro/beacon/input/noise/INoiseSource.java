package br.gov.inmetro.beacon.input.noise;

import java.io.IOException;

interface INoiseSource {
    String getNoise512Bits() throws IOException, InterruptedException;
}
