package co.edu.univalle.ticket.infrastructure.client;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import java.util.UUID;

@Component
public class OrderServiceClient {

    private final RestClient restClient;

    public OrderServiceClient(
            @Value("${services.order-service.url}")
            String baseUrl
    ) {
        this.restClient = RestClient.builder()
                .baseUrl(baseUrl)
                .build();
    }

    public OrderResponse getOrder(UUID orderId) {

        return restClient.get()
                .uri("/api/v1/orders/{id}", orderId)
                .retrieve()
                .body(OrderResponse.class);
    }
}