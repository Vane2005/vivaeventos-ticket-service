package co.edu.univalle.ticket.infrastructure.messaging;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    public static final String EXCHANGE = "vivaeventos.events";

    public static final String PAYMENT_APPROVED_QUEUE =
            "ticket-service.pago.aprobado";

    public static final String PAYMENT_APPROVED_ROUTING_KEY =
            "pago.aprobado";

    @Bean
    public TopicExchange exchange() {
        return new TopicExchange(EXCHANGE, true, false);
    }

    @Bean
    public Queue paymentApprovedQueue() {
        return QueueBuilder
                .durable(PAYMENT_APPROVED_QUEUE)
                .build();
    }

    @Bean
    public Binding paymentApprovedBinding(
            Queue paymentApprovedQueue,
            TopicExchange exchange
    ) {
        return BindingBuilder
                .bind(paymentApprovedQueue)
                .to(exchange)
                .with(PAYMENT_APPROVED_ROUTING_KEY);
    }

    @Bean
    public MessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate template = new RabbitTemplate(connectionFactory);
        template.setMessageConverter(jsonMessageConverter());
        return template;
    }
}