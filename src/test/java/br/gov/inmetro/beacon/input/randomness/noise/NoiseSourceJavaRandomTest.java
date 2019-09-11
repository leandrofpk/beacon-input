package br.gov.inmetro.beacon.input.randomness.noise;

import org.assertj.core.internal.Arrays;
import org.junit.Test;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import static org.junit.Assert.*;

public class NoiseSourceJavaRandomTest {

    @Test
    public void teste() throws NoSuchAlgorithmException {

        byte[] bytes = new byte[64];
        SecureRandom.getInstance("SHA1PRNG").nextBytes(bytes);

        System.out.println(bytesToHex(bytes));


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