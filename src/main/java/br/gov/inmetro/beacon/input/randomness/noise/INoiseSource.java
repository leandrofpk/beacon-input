package br.gov.inmetro.beacon.input.randomness.noise;

interface INoiseSource {
    String getNoise512Bits() throws Exception;
}
