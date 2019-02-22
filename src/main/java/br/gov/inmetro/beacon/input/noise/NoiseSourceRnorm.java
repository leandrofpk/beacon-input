package br.gov.inmetro.beacon.input.noise;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
    https://crunchify.com/how-to-run-windowsmac-commands-in-java-and-return-the-text-result/
 */

@Component
@Profile("default")
class NoiseSourceRnorm implements INoiseSource {

    @Override
    public String getNoise512Bits() throws IOException, InterruptedException {

        String s = "";

        try {

            Process p = Runtime.getRuntime().exec("rnorm --precision 40");

            BufferedReader stdInput = new BufferedReader(new InputStreamReader(p.getInputStream()));
//            BufferedReader stdError = new BufferedReader(new InputStreamReader(p.getErrorStream()));

            // read the output from the command
//            System.out.println("Here is the standard output of the command:\n");
            while ((s = stdInput.readLine()) != null) {
                return s;
            }

            // read any errors from the attempted command
//            System.out.println("Here is the standard error of the command (if any):\n");
//            while ((s = stdError.readLine()) != null) {
//                System.out.println(s);
//            }

        } catch (IOException e) {
            System.out.println("exception happened - here's what I know: ");
            e.printStackTrace();
            System.exit(-1);
        }

        return s;
    }

}
