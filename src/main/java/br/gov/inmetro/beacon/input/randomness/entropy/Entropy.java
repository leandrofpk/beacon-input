package br.gov.inmetro.beacon.input.randomness.entropy;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.ZonedDateTime;

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
    private int period;

    @NotNull
    private ZonedDateTime timeStamp;

    @NotNull
    private String deviceDescription;

    @NotNull
    private byte noiseSource;

    private boolean sent = true;

    Entropy sentRemote(boolean value){
        this.sent = value;
        return this;
    }

    public Entropy setSent(boolean sent) {
        this.sent = sent;
        return this;
    }
}
