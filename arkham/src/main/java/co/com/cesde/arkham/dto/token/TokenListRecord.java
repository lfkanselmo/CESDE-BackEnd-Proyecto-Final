package co.com.cesde.arkham.dto.token;

import co.com.cesde.arkham.entity.Token;

public record TokenListRecord(
        String token,
        boolean expired,
        boolean revoked
)
{
    public TokenListRecord(Token token){
        this(token.getToken(), token.isExpired(), token.isRevoked());
    }
}
