package com.archetype.keycloackadapter.vo;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
public  class UserInfoResponse {
    private String id;
    private Long createdTimestamp;
    private String username;
    private Boolean enabled;
    private Boolean totp;
    private Boolean emailVerified;
    private String firstName;
    private String lastName;
    private List<String> disableableCredentialTypes;
    private List<String> requiredActions;
    private Integer notBefore;
    private Access access; // Objeto anidado para el campo "access"

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Access {
        private Boolean manageGroupMembership;
        private Boolean view;
        private Boolean mapRoles;
        private Boolean impersonate;
        private Boolean manage;
    }
}
