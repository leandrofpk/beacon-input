package br.gov.inmetro.beacon.input.randomness.noise;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.security.SecureRandom;

@Component
@Profile({"validacao","homolog"})
class NoiseSourceValHomologJava implements INoiseSource {

    private final Environment environment;

    @Autowired
    NoiseSourceValHomologJava(Environment environment) {
        this.environment = environment;
    }

    @Override
    public String getNoise512Bits() throws Exception {
        byte[] bytes = new byte[64];
        SecureRandom.getInstance("NativePRNG").nextBytes(bytes);
        return bytesToHex(bytes);
    }

    private String bytesToHex(byte[] hash) {
        StringBuffer hexString = new StringBuffer();
        for (int i = 0; i < hash.length; i++) {
            String hex = Integer.toHexString(0xff & hash[i]);
            if(hex.length() == 1) hexString.append('0');
            hexString.append(hex);
        }
        return hexString.toString();
    }

}