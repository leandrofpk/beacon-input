package br.gov.inmetro.beacon.input.noise;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
    https://crunchify.com/how-to-run-windowsmac-commands-in-java-and-return-the-text-result/
 */

@Component
@Profile({"producao", "test"})
class NoiseSourceComScirePQ32MS implements INoiseSource {

    @Value("${beacon.entropy.command}")
    private String command;

    @Override
    public String getNoise512Bits() throws IOException, InterruptedException {
        String s = "";

        System.out.println("----------------------------------------------------------------------");

        try {

            System.out.println("Commando: " + command);
            Process p = Runtime.getRuntime().exec(command);

            BufferedReader stdInput = new BufferedReader(new InputStreamReader(p.getInputStream()));
//            BufferedReader stdError = new BufferedReader(new InputStreamReader(p.getErrorStream()));

            // read the output from the command
//            System.out.println("Here is the standard output of the command:\n");
            int linha = 1;
            while ((s = stdInput.readLine()) != null) {

                System.out.println("---------------------------------");
                System.out.println(s);
                System.out.println("Numero:" + s.replaceAll(" ", ""));

                if (linha == 58){
                    return s.replaceAll(" ", "");
                }
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
