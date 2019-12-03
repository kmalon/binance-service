package pl.km.client.controller;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.km.client.infrastrukture.RestFeignClient;

@Slf4j
@RestController
@RequestMapping("/auth")
public class AuthClientController {
    private static final Logger LOGGER = LoggerFactory.getLogger(AuthClientController.class);
    private RestFeignClient restFeignClient;

    public AuthClientController(RestFeignClient restFeignClient) {
        this.restFeignClient = restFeignClient;
    }

    @GetMapping(path = "/test")
    public ResponseEntity authTest() {
        LOGGER.info("User authenticated");
        String categories = restFeignClient.getCategories();
        return ResponseEntity.ok("authenticated");
    }
}