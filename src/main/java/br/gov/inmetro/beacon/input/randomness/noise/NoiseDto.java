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

    private String version;

    private String frequency;

    @JsonIgnore
    private LocalDateTime timeStampDateTime;

    private Long timeStamp;

    private String statusCode;

    // talvez os campos abaixo sejam retirados

    private String seedValue = "seedValue";

    private String previousOutputValue = "previousOutputValue";

    private String signatureValue = "signatureValue";

    private String outputValue;

    public NoiseDto() {
    }

    public NoiseDto(LocalDateTime timeStamp, String rawData, String chain,
                    String frequency, String version, String statusCode) {
        this.timeStampDateTime = timeStamp;
        this.rawData = rawData;
        this.chain = chain;
        this.frequency = frequency;
        this.version = version;
        this.statusCode = statusCode;
        this.timeStamp = this.timeStampDateTime.atZone(ZoneId.of("America/Sao_Paulo")).toInstant().toEpochMilli();
        this.outputValue = rawData;

    }

//    public NoiseDto(LocalDateTime timeStamp, String rawData, String frequency) {
//        this.timeStampDateTime = timeStamp;
//        this.rawData = rawData;
//        this.frequency = frequency;
//    }
}
