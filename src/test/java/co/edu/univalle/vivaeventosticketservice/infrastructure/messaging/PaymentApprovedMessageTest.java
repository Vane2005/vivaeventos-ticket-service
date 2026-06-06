package co.edu.univalle.vivaeventosticketservice.infrastructure.messaging;

import co.edu.univalle.ticket.infrastructure.messaging.PaymentApprovedMessage;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PaymentApprovedMessageTest {

    @Test
    void shouldCreateRecord() {

        UUID paymentId = UUID.randomUUID();
        UUID orderId = UUID.randomUUID();

        PaymentApprovedMessage message =
                new PaymentApprovedMessage(
                        paymentId,
                        orderId,
                        BigDecimal.TEN,
                        "COP",
                        "REF123",
                        "test@mail.com",
                        Instant.now()
                );

        assertEquals(paymentId, message.paymentId());
        assertEquals(orderId, message.orderId());
        assertEquals("COP", message.currency());
    }
}
