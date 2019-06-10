package br.gov.inmetro.beacon.input.randomness.noise;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.ZoneId;

@Data
public class NoiseDto implements Serializable {

    private final String rawData;

    private final String chain;

    private final String frequency;

    private final String noiseSource;

    @JsonIgnore
    private final LocalDateTime timeStampDateTime;

    private final Long timeStamp;

    public NoiseDto(LocalDateTime timeStamp, String rawData, String chain,
                    String frequency, String noiseSource) {
        this.timeStampDateTime = timeStamp;
        this.rawData = rawData;
        this.chain = chain;
        this.frequency = frequency;
        this.timeStamp = this.timeStampDateTime.atZone(ZoneId.of("America/Sao_Paulo")).toInstant().toEpochMilli();
        this.noiseSource = noiseSource;
    }

    @Override
    public String toString() {
        return "NoiseDto{" +
                "rawData='" + rawData + '\'' +
                ", chain='" + chain + '\'' +
                ", frequency='" + frequency + '\'' +
                ", noiseSource='" + noiseSource + '\'' +
                ", timeStampDateTime=" + timeStampDateTime +
                ", timeStamp=" + timeStamp +
                '}';
    }
}
