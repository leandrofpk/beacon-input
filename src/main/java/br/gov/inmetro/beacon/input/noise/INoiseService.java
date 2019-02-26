package br.gov.inmetro.beacon.input.noise;

import java.io.IOException;

public interface INoiseService {
    String get512Bits() throws IOException, InterruptedException, DeviceException;
}
