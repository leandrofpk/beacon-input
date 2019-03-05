package br.gov.inmetro.beacon.input.infra;

public interface EmailAvisoService {

    void sendSimpleMessage(String subject,
                           StringBuilder body);
}
