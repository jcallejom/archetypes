package com.archetype.keycloackadapter.vo;

import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

import java.util.UUID;

@Data
@Builder
@Jacksonized
public final class RolResponse {
    private final String id;//id  del rol 
    private final String name;//nombre del rol

    
}
