package co.edu.univalle.vivaeventosticketservice.client;

import co.edu.univalle.ticket.infrastructure.client.OrderResponse;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

class OrderResponseTest {

    @Test
    void shouldSetAndGetFields() {

        OrderResponse response = new OrderResponse();

        UUID id = UUID.randomUUID();

        response.setId(id);
        response.setCustomerEmail("test@mail.com");
        response.setQuantity(2);

        assertEquals(id, response.getId());
        assertEquals("test@mail.com", response.getCustomerEmail());
        assertEquals(2, response.getQuantity());
    }
}
