package co.edu.univalle.vivaeventosticketservice.application.service;

import co.edu.univalle.ticket.application.service.QrImageService;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class QrImageServiceTest {

    private final QrImageService service = new QrImageService();

    @Test
    void shouldGenerateQrImage() throws Exception {

        byte[] image = service.generateQrImage("TEST-QR");

        assertNotNull(image);
        assertTrue(image.length > 0);
    }
}