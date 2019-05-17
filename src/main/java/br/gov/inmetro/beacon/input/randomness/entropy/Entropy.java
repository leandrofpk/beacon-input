package br.gov.inmetro.beacon.input.randomness.entropy;

import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
@Data
public class Entropy {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull
    @Lob
    private String rawData;

    @NotNull
    private String chain;

    @Length(max = 20)
    private String versionBeacon;

    @NotNull
    private String frequency;

    @NotNull
    @DateTimeFormat(pattern = "dd/MM/yyyy hh:mm a")
    private LocalDateTime timeStamp;

    private Long unixTimeStamp;

    @Lob
    private String seedValue;

    @Lob
    private String previousOutputValue;

    @Lob
    private String signatureValue;

    @Lob
    private String outputValue;

    @Length(max = 20)
    private String statusCode;

    @Enumerated(EnumType.STRING)
    @NotNull
    private OriginEnum origin;

    private boolean sent = false;

    Entropy sentToRemote(){
        this.sent = true;
        return this;
    }

}
