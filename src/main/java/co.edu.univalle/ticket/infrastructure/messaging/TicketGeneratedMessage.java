package co.edu.univalle.ticket.infrastructure.messaging;

import java.util.List;

public record TicketGeneratedMessage(
        String customerEmail,
        List<String> qrCodes
) {}