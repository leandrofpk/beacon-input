package br.gov.inmetro.beacon.input.randomness.entropy;

import lombok.Data;

import java.io.Serializable;
import java.time.ZonedDateTime;

@Data
public class EntropyDto implements Serializable {

    private final Long id;

    private final String rawData;

    private final int period;

    private final byte noiseSource;

    private final ZonedDateTime timeStamp;

    public EntropyDto(ZonedDateTime timeStamp, String rawData, String chain,
                      int period, byte noiseSource) {
        this.id = null;
        this.timeStamp = timeStamp;
        this.rawData = rawData;
        this.period = period;
        this.noiseSource = noiseSource;
    }

    public EntropyDto(Entropy entropy){
        this.id = entropy.getId();
        this.timeStamp = entropy.getTimeStamp();
        this.period = entropy.getPeriod();
        this.noiseSource = entropy.getNoiseSource();
        this.rawData = entropy.getRawData();
    }

    @Override
    public String toString() {
        return "EntropyDto{" +
                "id=" + id +
                ", rawData='" + rawData + '\'' +
                ", period=" + period +
                ", noiseSource=" + noiseSource +
                ", timeStamp=" + timeStamp +
                '}';
    }
}
