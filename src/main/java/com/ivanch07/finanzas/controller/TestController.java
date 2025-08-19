package com.ivanch07.finanzas.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @GetMapping("/api/test/secure")
    public String secureEndpoint() {
        return "Accediste al endpoint protegido con un JWT válido!";
    }

    @GetMapping("/api/test/public")
    public String publicEndpoint() {
        return "Este endpoint es público, no requiere token.";
    }
}
