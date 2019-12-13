package pl.km.client.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/public")
public class ClientController {

    @GetMapping(path = "/noauth")
    public ResponseEntity<String> test(@RequestParam("api-key") String key, @RequestParam("sec-key") char[] seckey) {
        log.info("User not authenticated");
        return ResponseEntity.ok("Not authenticated");
    }
}