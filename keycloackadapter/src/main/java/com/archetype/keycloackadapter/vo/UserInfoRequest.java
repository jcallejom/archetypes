package com.archetype.keycloackadapter.vo;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data 
@NoArgsConstructor 
@AllArgsConstructor
@Builder
public class UserInfoRequest {
    private String username;
    private String firstName;
    private String lastName;
    private Boolean enabled; 
    private List<Credential> credentials; // Lista de objetos Credential

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class Credential {
        private String type;
        private String value;
        private Boolean temporary;
    }
}
