package br.gov.inmetro.beacon.input.randomness.noise;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.ZoneId;

@Data
public class NoiseDto implements Serializable {

    private String rawData;

    private String chain;

    private String frequency;

    @JsonIgnore
    private LocalDateTime timeStampDateTime;

    private Long timeStamp;

    public NoiseDto(LocalDateTime timeStamp, String rawData, String chain,
                    String frequency) {
        this.timeStampDateTime = timeStamp;
        this.rawData = rawData;
        this.chain = chain;
        this.frequency = frequency;
        this.timeStamp = this.timeStampDateTime.atZone(ZoneId.of("America/Sao_Paulo")).toInstant().toEpochMilli();
    }

}
