package br.gov.inmetro.beacon.input.randomness.noise;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Random;

@Component
@Profile("default")
class NoiseSourceJavaRandom implements INoiseSource {

    @Override
    public String getNoise512Bits() {
         return bytesToHex(gerarNumero512Bits().getBytes());
    }

    private String gerarNumero512Bits(){
        Random gerador = new Random();
        String string512 = "";
        for (int i = 0; i < 512; i++) {
            string512 = string512 + gerador.nextInt(90);
        }
        return string512;
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
