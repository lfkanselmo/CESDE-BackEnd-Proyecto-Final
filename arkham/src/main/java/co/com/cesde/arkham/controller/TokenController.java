package co.com.cesde.arkham.controller;

import co.com.cesde.arkham.dto.token.ConsultTokenRecord;
import co.com.cesde.arkham.dto.token.TokenListRecord;
import co.com.cesde.arkham.entity.Token;
import co.com.cesde.arkham.infra.exception.NotFoundValidation;
import co.com.cesde.arkham.repository.TokenRepository;
import jakarta.validation.Valid;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/token")
public class TokenController {
    @Autowired
    private TokenRepository tokenRepository;

    @PostMapping("/check")
    public ResponseEntity<TokenListRecord> checkToken(@RequestBody @Valid ConsultTokenRecord token) throws NotFoundValidation {
        Token tokenReturned = tokenRepository.findByToken(token.token())
                .orElseThrow(() -> new NotFoundValidation("no encontrado"));
        TokenListRecord tokenListRecord = new TokenListRecord(tokenReturned);
        return ResponseEntity.ok(tokenListRecord);
    }
}
