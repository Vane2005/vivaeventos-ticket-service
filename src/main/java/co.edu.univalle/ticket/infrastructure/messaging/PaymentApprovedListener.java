package co.edu.univalle.ticket.infrastructure.messaging;

import co.edu.univalle.ticket.infrastructure.client.OrderResponse;
import co.edu.univalle.ticket.infrastructure.client.OrderServiceClient;
import co.edu.univalle.ticket.application.service.QrGeneratorService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.List;

@Component
@Profile("rabbit")
public class PaymentApprovedListener {
    private final OrderServiceClient orderServiceClient;
    private final QrGeneratorService qrGeneratorService;
    private final TicketEventPublisher ticketEventPublisher;

    public PaymentApprovedListener(
            OrderServiceClient orderServiceClient,
            QrGeneratorService qrGeneratorService,
            TicketEventPublisher ticketEventPublisher
    ) {
        this.orderServiceClient = orderServiceClient;
        this.qrGeneratorService = qrGeneratorService;
        this.ticketEventPublisher = ticketEventPublisher;
    }

    @RabbitListener(queues = RabbitMQConfig.PAYMENT_APPROVED_QUEUE)
    public void consume(PaymentApprovedMessage message) {
        OrderResponse order = orderServiceClient.getOrder(message.orderId());
        List<String> qrCodes = new ArrayList<>();
        for (int i = 0; i < order.getQuantity(); i++) {
            qrCodes.add(qrGeneratorService.generateQrContent());
        }
        ticketEventPublisher.publish(new TicketGeneratedMessage(
                order.getCustomerEmail(),
                qrCodes
        ));
    }
}