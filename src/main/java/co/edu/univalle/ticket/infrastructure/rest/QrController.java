package co.edu.univalle.ticket.infrastructure.rest;

import co.edu.univalle.ticket.application.service.QrGeneratorService;
import co.edu.univalle.ticket.application.service.QrImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/tickets")
@RequiredArgsConstructor
public class QrController {

    private final QrGeneratorService qrGeneratorService;
    private final QrImageService qrImageService;

    @GetMapping(value = "/qr-test", produces = MediaType.IMAGE_PNG_VALUE)
    public ResponseEntity<byte[]> testQr() throws Exception {
        String content = qrGeneratorService.generateQrContent();
        byte[] image = qrImageService.generateQrImage(content);
        return ResponseEntity.ok(image);
    }
}