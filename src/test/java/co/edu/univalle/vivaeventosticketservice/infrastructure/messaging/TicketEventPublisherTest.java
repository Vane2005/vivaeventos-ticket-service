package co.edu.univalle.vivaeventosticketservice.infrastructure.messaging;

import co.edu.univalle.ticket.infrastructure.messaging.TicketEventPublisher;
import co.edu.univalle.ticket.infrastructure.messaging.TicketGeneratedMessage;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import org.springframework.amqp.rabbit.core.RabbitTemplate;

import java.util.List;

import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class TicketEventPublisherTest {

    @Mock
    private RabbitTemplate rabbitTemplate;

    @InjectMocks
    private TicketEventPublisher publisher;

    @Test
    void shouldPublishTicketGeneratedEvent() {

        TicketGeneratedMessage message =
                new TicketGeneratedMessage(
                        "test@test.com",
                        List.of("QR1", "QR2")
                );

        publisher.publish(message);

        verify(rabbitTemplate)
                .convertAndSend(
                        "vivaeventos.events",
                        "ticket.generated",
                        message
                );
    }
}