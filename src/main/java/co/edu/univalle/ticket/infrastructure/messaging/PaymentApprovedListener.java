package co.edu.univalle.ticket.infrastructure.messaging;

import co.edu.univalle.ticket.domain.model.Ticket;
import co.edu.univalle.ticket.infrastructure.client.OrderResponse;
import co.edu.univalle.ticket.infrastructure.client.OrderServiceClient;
import co.edu.univalle.ticket.application.service.QrGeneratorService;
import co.edu.univalle.ticket.infrastructure.persistence.TicketRepository;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Component
@Profile("rabbit")
public class PaymentApprovedListener {
    private final OrderServiceClient orderServiceClient;
    private final QrGeneratorService qrGeneratorService;
    private final TicketEventPublisher ticketEventPublisher;
    private final TicketRepository ticketRepository;

    public PaymentApprovedListener(
            OrderServiceClient orderServiceClient,
            QrGeneratorService qrGeneratorService,
            TicketEventPublisher ticketEventPublisher,
            TicketRepository ticketRepository
    ) {
        this.orderServiceClient = orderServiceClient;
        this.qrGeneratorService = qrGeneratorService;
        this.ticketEventPublisher = ticketEventPublisher;
        this.ticketRepository = ticketRepository;
    }

    @RabbitListener(queues = RabbitMQConfig.PAYMENT_APPROVED_QUEUE)
    public void consume(PaymentApprovedMessage message) {
        OrderResponse order = orderServiceClient.getOrder(message.orderId());
        List<String> qrCodes = new ArrayList<>();

        for (int i = 0; i < order.getQuantity(); i++) {
            String qrCode = qrGeneratorService.generateQrContent(); // UUID aleatorio

            Ticket ticket = new Ticket();
            ticket.setOrderId(message.orderId());
            ticket.setQrCode(qrCode);
            ticket.setCreatedAt(Instant.now());
            ticketRepository.save(ticket); // persiste antes de publicar

            qrCodes.add(qrCode);
        }

        ticketEventPublisher.publish(new TicketGeneratedMessage(
                order.getCustomerEmail(),
                qrCodes
        ));
    }
}