package br.gov.inmetro.beacon.input.randomness.entropy;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.ZoneId;

@Data
public class EntropyDto implements Serializable {

    private final Long id;

    private final String rawData;

    private final String chain;

    private final String frequency;

    private final String noiseSource;

    @JsonIgnore
    private final LocalDateTime timeStampDateTime;

    private final Long timeStamp;

    public EntropyDto(LocalDateTime timeStamp, String rawData, String chain,
                      String frequency, String noiseSource) {
        this.id = null;
        this.timeStampDateTime = timeStamp;
        this.rawData = rawData;
        this.chain = chain;
        this.frequency = frequency;
        this.timeStamp = this.timeStampDateTime.atZone(ZoneId.of("America/Sao_Paulo")).toInstant().toEpochMilli();
        this.noiseSource = noiseSource;
    }

    public EntropyDto(Entropy entropy){
        this.id = entropy.getId();
        this.timeStamp = entropy.getUnixTimeStamp();
        this.chain = entropy.getChain();
        this.frequency = entropy.getFrequency();
        this.noiseSource = entropy.getNoiseSource();
        this.rawData = entropy.getRawData();
        this.timeStampDateTime = entropy.getTimeStamp();
    }

    @Override
    public String toString() {
        return "EntropyDto{" +
                "rawData='" + rawData + '\'' +
                ", chain='" + chain + '\'' +
                ", frequency='" + frequency + '\'' +
                ", noiseSource='" + noiseSource + '\'' +
                ", timeStampDateTime=" + timeStampDateTime +
                ", timeStamp=" + timeStamp +
                '}';
    }
}
