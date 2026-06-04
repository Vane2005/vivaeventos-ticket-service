package co.edu.univalle.ticket.infrastructure.messaging;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("rabbit")
public class TicketEventPublisher {
    private final RabbitTemplate rabbitTemplate;

    public TicketEventPublisher(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void publish(TicketGeneratedMessage message) {
        rabbitTemplate.convertAndSend(
                "vivaeventos.events",
                "ticket.generated",
                message
        );
    }
}