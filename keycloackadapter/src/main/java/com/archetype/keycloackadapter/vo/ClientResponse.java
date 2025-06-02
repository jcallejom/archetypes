package com.archetype.keycloackadapter.vo;

import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

import java.util.UUID;

@Data
@Builder
@Jacksonized
public final class ClientResponse {
    private final String id;//nombre del cliente

    
}
