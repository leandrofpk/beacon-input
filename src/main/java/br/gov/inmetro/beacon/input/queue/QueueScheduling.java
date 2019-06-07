package br.gov.inmetro.beacon.input.queue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@EnableScheduling
public class QueueScheduling {

    private OrderQueueSender orderQueueSender;

    @Autowired
    public QueueScheduling(OrderQueueSender orderQueueSender) {
        this.orderQueueSender = orderQueueSender;
    }

    @Scheduled(cron = "*/10 * * * * *")
    public void teste() {
        orderQueueSender.send(LocalDateTime.now().toString());
    }
}
