package co.edu.univalle.ticket.infrastructure.messaging;

import org.springframework.stereotype.Component;

import java.util.List;

public record TicketGeneratedMessage(
        String customerEmail,
        List<String> qrCodes
) {}