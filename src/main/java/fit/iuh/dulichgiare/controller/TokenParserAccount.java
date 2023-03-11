package fit.iuh.dulichgiare.controller;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import fit.iuh.dulichgiare.config.JwtTokenService;

@RestController
@RequestMapping("/token")
public class TokenParserAccount {
    @Autowired
    private JwtTokenService jwtTokenService;

    @GetMapping("/{token}")
    private Long getUserIdFromToken(@PathVariable String token) throws InvalidKeyException, NoSuchAlgorithmException {
        return jwtTokenService.getUserIdFromToken(token);
    }
}
