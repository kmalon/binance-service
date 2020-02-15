package pl.km.client.api;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/auth")
class AuthClientController {

    @GetMapping(path = "/test")
    ResponseEntity<String> authTest() {
        log.info("User authenticated");
        return ResponseEntity.ok("authenticated");
    }
}