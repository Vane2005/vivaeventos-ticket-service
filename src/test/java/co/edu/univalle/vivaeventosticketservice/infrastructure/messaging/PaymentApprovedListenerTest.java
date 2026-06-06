package co.edu.univalle.vivaeventosticketservice.infrastructure.messaging;

import co.edu.univalle.ticket.application.service.QrGeneratorService;
import co.edu.univalle.ticket.infrastructure.client.OrderResponse;
import co.edu.univalle.ticket.infrastructure.client.OrderServiceClient;

import co.edu.univalle.ticket.infrastructure.messaging.PaymentApprovedListener;
import co.edu.univalle.ticket.infrastructure.messaging.PaymentApprovedMessage;
import co.edu.univalle.ticket.infrastructure.messaging.TicketEventPublisher;
import co.edu.univalle.ticket.infrastructure.messaging.TicketGeneratedMessage;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PaymentApprovedListenerTest {

    @Mock
    private OrderServiceClient orderServiceClient;

    @Mock
    private QrGeneratorService qrGeneratorService;

    @Mock
    private TicketEventPublisher ticketEventPublisher;

    @InjectMocks
    private PaymentApprovedListener listener;

    @Test
    void shouldGenerateQrCodesAndPublishEvent() {

        UUID orderId = UUID.randomUUID();

        PaymentApprovedMessage message =
                new PaymentApprovedMessage(
                        UUID.randomUUID(),
                        orderId,
                        null,
                        null,
                        null,
                        "user@test.com",
                        null
                );

        OrderResponse order = new OrderResponse();
        order.setQuantity(2);
        order.setCustomerEmail("user@test.com");

        when(orderServiceClient.getOrder(orderId))
                .thenReturn(order);

        when(qrGeneratorService.generateQrContent())
                .thenReturn("QR1", "QR2");

        listener.consume(message);

        ArgumentCaptor<TicketGeneratedMessage> captor =
                ArgumentCaptor.forClass(TicketGeneratedMessage.class);

        verify(ticketEventPublisher).publish(captor.capture());

        TicketGeneratedMessage generated = captor.getValue();

        assertEquals(2, generated.qrCodes().size());
        assertEquals("user@test.com", generated.customerEmail());
    }
}