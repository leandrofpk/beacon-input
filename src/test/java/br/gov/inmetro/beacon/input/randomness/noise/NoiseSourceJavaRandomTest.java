package br.gov.inmetro.beacon.input.randomness.noise;

import org.assertj.core.internal.Arrays;
import org.junit.Test;

import java.security.SecureRandom;

import static org.junit.Assert.*;

public class NoiseSourceJavaRandomTest {

    @Test
    public void teste(){
        SecureRandom random = new SecureRandom();
        byte bytes[] = new byte[64];
        random.nextBytes(bytes);




    }


}