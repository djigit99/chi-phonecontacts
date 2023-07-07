package dev.djigit.phonecontacts.security;

import dev.djigit.phonecontacts.entity.PhoneContactUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private static final Logger LOGGER = LoggerFactory.getLogger(AuthController.class);

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping
    public ResponseEntity<Map<String, Object>> login(@RequestBody PhoneContactUser phoneContactUser) {
        LOGGER.debug("Log in ...");
        Map<String, Object> loginInfo = authService.createLoginInfo(phoneContactUser);
        return new ResponseEntity<>(loginInfo, HttpStatus.OK);
    }
}
