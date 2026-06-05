package co.edu.univalle.ticket.infrastructure.persistence;

import co.edu.univalle.ticket.domain.model.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface TicketRepository extends JpaRepository<Ticket, UUID> { }