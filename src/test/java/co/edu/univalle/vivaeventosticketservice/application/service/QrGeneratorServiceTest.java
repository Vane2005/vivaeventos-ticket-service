package co.edu.univalle.vivaeventosticketservice.application.service;

import co.edu.univalle.ticket.application.service.QrGeneratorService;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class QrGeneratorServiceTest {

    private final QrGeneratorService service = new QrGeneratorService();

    @Test
    void shouldGenerateUniqueQrContent() {

        String qr1 = service.generateQrContent();
        String qr2 = service.generateQrContent();

        assertNotNull(qr1);
        assertNotNull(qr2);
        assertNotEquals(qr1, qr2);
    }
}