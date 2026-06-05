package co.edu.univalle.ticket.domain.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "tickets")
@Getter @Setter
public class Ticket {
    @Id
    @GeneratedValue
    private UUID id;

    @Column(name = "order_id", nullable = false)
    private UUID orderId;

    @Column(name = "qr_code", nullable = false, unique = true)
    private String qrCode; // UUID único, columna con constraint UNIQUE

    @Column(name = "created_at", nullable = false)
    private Instant createdAt;

}