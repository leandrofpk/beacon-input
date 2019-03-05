package br.gov.inmetro.beacon.input.randomness.noise;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.io.IOException;

/*
    https://crunchify.com/how-to-run-windowsmac-commands-in-java-and-return-the-text-result/
 */

@Component
@Profile("default")
class NoiseSourceRnorm implements INoiseSource {

    @Override
    public String getNoise512Bits() {

        String s = "";

//        try {

//            Process p = Runtime.getRuntime().exec("rnorm --precision 40");

            return "B54CB7ECD383D7542F5C896D9B26D8998A4E1CA762ED3139B8276D803F66B6E1E1BC76D67D4A8DA0B0E4E4AF51FD594FD62DF67A7021F2E6ECDC3C1FBFC5711B";

//            BufferedReader stdInput = new BufferedReader(new InputStreamReader(p.getInputStream()));
//            BufferedReader stdError = new BufferedReader(new InputStreamReader(p.getErrorStream()));

            // read the output from the command
//            System.out.println("Here is the standard output of the command:\n");
//            while ((s = stdInput.readLine()) != null) {
////                return s;
//                return "B54CB7ECD383D7542F5C896D9B26D8998A4E1CA762ED3139B8276D803F66B6E1E1BC76D67D4A8DA0B0E4E4AF51FD594FD62DF67A7021F2E6ECDC3C1FBFC5711B";
//            }

            // read any errors from the attempted command
//            System.out.println("Here is the standard error of the command (if any):\n");
//            while ((s = stdError.readLine()) != null) {
//                System.out.println(s);
//            }

//        } catch (IOException e) {
//            System.out.println("exception happened - here's what I know: ");
//            e.printStackTrace();
//            System.exit(-1);
        }

//        return s;
//    }

}
