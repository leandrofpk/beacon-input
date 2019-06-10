package br.gov.inmetro.beacon.input.queue;

import br.gov.inmetro.beacon.input.randomness.noise.NoiseDto;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BeaconPulseQueueSender {

    private final RabbitTemplate rabbitTemplate;

    private static final String EXCHANGE = "beacon_pulse_data";

    private static final String ROUTING_KEY = "pulse.regular";

    @Autowired
    public BeaconPulseQueueSender(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void send(NoiseDto noiseDto) {
        rabbitTemplate.convertAndSend(EXCHANGE, ROUTING_KEY, noiseDto);
    }

}
