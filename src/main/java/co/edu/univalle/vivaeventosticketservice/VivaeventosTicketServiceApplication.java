package co.edu.univalle.vivaeventosticketservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "co.edu.univalle.ticket.infrastructure.persistence")
@EntityScan(basePackages = "co.edu.univalle.ticket.domain.model")
public class VivaeventosTicketServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(VivaeventosTicketServiceApplication.class, args);
    }
}