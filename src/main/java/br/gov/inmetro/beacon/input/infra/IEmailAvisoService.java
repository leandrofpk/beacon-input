package br.gov.inmetro.beacon.input.infra;

public interface IEmailAvisoService {

    void sendSimpleMessage(String subject,
                           StringBuilder body);
}
