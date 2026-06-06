package co.edu.univalle.vivaeventosticketservice.infrastructure.rest;

import co.edu.univalle.ticket.application.service.QrGeneratorService;
import co.edu.univalle.ticket.application.service.QrImageService;
import co.edu.univalle.ticket.infrastructure.rest.QrController;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class QrControllerTest {

    @Mock
    private QrGeneratorService qrGeneratorService;

    @Mock
    private QrImageService qrImageService;

    @InjectMocks
    private QrController controller;

    @Test
    void shouldReturnQrImage() throws Exception {

        when(qrGeneratorService.generateQrContent())
                .thenReturn("QR-CONTENT");

        byte[] image = new byte[]{1,2,3};

        when(qrImageService.generateQrImage("QR-CONTENT"))
                .thenReturn(image);

        ResponseEntity<byte[]> response = controller.testQr();

        assertEquals(200, response.getStatusCode().value());
        assertArrayEquals(image, response.getBody());

        verify(qrGeneratorService).generateQrContent();
        verify(qrImageService).generateQrImage("QR-CONTENT");
    }
}
