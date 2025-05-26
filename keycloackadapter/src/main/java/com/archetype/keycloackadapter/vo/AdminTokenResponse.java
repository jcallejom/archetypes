package com.archetype.keycloackadapter.vo;

import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

import java.util.UUID;

@Data
@Builder
@Jacksonized
public final class AdminTokenResponse {
    private final String access_token;
    private final String refresh_token;
    private final String token_type;
    private final String scope;
    
}
