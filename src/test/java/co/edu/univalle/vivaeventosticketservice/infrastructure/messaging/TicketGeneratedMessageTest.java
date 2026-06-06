package co.edu.univalle.vivaeventosticketservice.infrastructure.messaging;

import co.edu.univalle.ticket.infrastructure.messaging.TicketGeneratedMessage;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TicketGeneratedMessageTest {

    @Test
    void shouldCreateRecord() {

        TicketGeneratedMessage message =
                new TicketGeneratedMessage(
                        "test@mail.com",
                        List.of("QR1", "QR2")
                );

        assertEquals("test@mail.com", message.customerEmail());
        assertEquals(2, message.qrCodes().size());
    }
}