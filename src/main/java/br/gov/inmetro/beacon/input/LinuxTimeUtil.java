package br.gov.inmetro.beacon.input;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

public class LinuxTimeUtil {

    public static LocalDateTime longToLocalDateTime(String timestamp) {
        LocalDateTime localDateTime = LocalDateTime.ofInstant(Instant.ofEpochMilli(new Long(timestamp)), ZoneId.of("America/Sao_Paulo"));
        return localDateTime;
    }


//    private LocalDateTime longToLocalDateTime(String data) {
//        Long millis = new Long(data);
//        if (data.length() == 10) {
//            millis = millis * 1000;
//        }
//
//        // atual
//        LocalDateTime localDateTime = LocalDateTime.ofInstant(Instant.ofEpochMilli(millis),
//                ZoneId.of("America/Sao_Paulo")).truncatedTo(ChronoUnit.MINUTES);
//
//        return localDateTime;
//    }

}
