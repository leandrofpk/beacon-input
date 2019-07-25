package br.gov.inmetro.beacon.input.randomness.noise;

import br.gov.inmetro.beacon.input.BeaconInputApplication;
import br.gov.inmetro.beacon.input.exceptions.NoiseSourceReadError;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/*
    https://crunchify.com/how-to-run-windowsmac-commands-in-java-and-return-the-text-result/
*/
@Component
@Profile({"producao", "test"})
class NoiseSourceComScirePQ32MS implements INoiseSource {

    @Value("${beacon.entropy.command}")
    private String command;

    private static final Logger logger = LoggerFactory.getLogger(NoiseSourceComScirePQ32MS.class);

    @Override
    public String getNoise512Bits() throws NoiseSourceReadError {
        String s;

        try {

            Process p = Runtime.getRuntime().exec(command);

            BufferedReader stdInput = new BufferedReader(new InputStreamReader(p.getInputStream()));

            // read the output from the command
//            System.out.println("Here is the standard output of the command:\n");
            int linha = 1;

//            String collect = stdInput.lines().collect(Collectors.joining());
//
//            if (StringUtils.isEmpty(collect)){
////                logger.error("DEVICE: device not available");
//                throw new NoiseSourceReadError("Device not available");
//            }

            while ((s = stdInput.readLine()) != null) {
//                logger.warn("S:" + s);
                if (linha == 57){
                    return s.replaceAll(" ", "");
                }
                linha++;
            }

//            logger.warn("Linha:" + linha);
            if (linha == 1){
                throw new NoiseSourceReadError("Device not available");
            }

        } catch (Exception e){
            throw new NoiseSourceReadError(e.getMessage());
        }

        return s;
    }

}
