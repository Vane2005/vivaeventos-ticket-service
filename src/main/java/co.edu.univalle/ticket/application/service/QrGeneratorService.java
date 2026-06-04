package co.edu.univalle.ticket.application.service;

import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class QrGeneratorService {

    public String generateQrContent() {
        return UUID.randomUUID().toString();
    }
}