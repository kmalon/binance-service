package pl.km.client.controller;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/auth")
public class AuthClientController {
    private static final Logger LOGGER = LoggerFactory.getLogger(AuthClientController.class);

    @GetMapping(path = "/test")
    public ResponseEntity authTest() {
        LOGGER.info("User authenticated");
        return ResponseEntity.ok("authenticated");
    }
}