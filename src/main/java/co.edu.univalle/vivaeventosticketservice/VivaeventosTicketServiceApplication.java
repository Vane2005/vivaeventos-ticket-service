package co.edu.univalle.vivaeventosticketservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {
        "co.edu.univalle.vivaeventosticketservice",
        "co.edu.univalle.ticket"
})
public class VivaeventosTicketServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(
                VivaeventosTicketServiceApplication.class,
                args
        );
    }
}