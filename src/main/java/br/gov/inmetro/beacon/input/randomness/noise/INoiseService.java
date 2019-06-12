package br.gov.inmetro.beacon.input.randomness.noise;

public interface INoiseService {
    NoiseDto getNoise() throws Exception;
    NoiseDto getNoise(String beaconNoiseSource) throws Exception;
    NoiseDto getNoise(String chain, String beaconNoiseSource) throws Exception;
}
