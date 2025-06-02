/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.archetype.keycloackadapter.service;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.archetype.keycloackadapter.exception.BussinesRuleException;
import com.archetype.keycloackadapter.vo.AdminTokenResponse;
import com.archetype.keycloackadapter.vo.ClientResponse;
import com.archetype.keycloackadapter.vo.CreateUserCommand;
import com.archetype.keycloackadapter.vo.DeleteUserCommand;
import com.archetype.keycloackadapter.vo.UserInfoRequest;
import com.archetype.keycloackadapter.vo.UserInfoRequest.Credential;
import com.archetype.keycloackadapter.vo.UserInfoResponse;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

/**
 *
 * @author jcallejo
 */
@Slf4j
@Service
public class KeycloakRestService {

    @Autowired
    private RestTemplate restTemplate;
    
    @Value("${keycloak.user-uri}")
    private String userUri;
    @Value("${keycloak.admin-token-uri}")
    private String adminTokenUri;
    @Value("${keycloak.client-uri}")
    private String clientUri;

    @Value("${keycloak.token-uri}")
    private String keycloakTokenUri;

    @Value("${keycloak.user-info-uri}")
    private String keycloakUserInfo;

    @Value("${keycloak.logout}")
    private String keycloakLogout;

    @Value("${keycloak.client-id}")
    private String clientId;

    @Value("${keycloak.authorization-grant-type}")
    private String grantType;
    
    @Value("${keycloak.authorization-grant-type-refresh}")
    private String grantTypeRefresh;

    @Value("${keycloak.client-secret}")
    private String clientSecret;

    @Value("${keycloak.scope}")
    private String scope;

    /**
     *  login by using username and password to keycloak, and capturing token on response body
     *
     * @param username
     * @param password
     * @return
     */
    public String login(String username, String password) {
        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add("username",username);
        map.add("password",password);
        map.add("client_id",clientId);
        map.add("grant_type",grantType);
        map.add("client_secret",clientSecret);
        map.add("scope",scope);

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(map, new HttpHeaders());
        return restTemplate.postForObject(keycloakTokenUri, request, String.class);
    }

    /**
     *  a successful user token will generate http code 200, other than that will create an exception
     *
     * @param token
     * @return
     * @throws Exception
     */
    public String checkValidity(String token) throws Exception {
        return getUserInfo(token);
    }

    /**
     *  logging out and disabling active token from keycloak
     *
     * @param refreshToken
     */
    public void logout(String refreshToken) throws Exception {
        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add("client_id",clientId);
        map.add("client_secret",clientSecret);
        map.add("refresh_token",refreshToken);

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(map, null);
        restTemplate.postForObject(keycloakLogout, request, String.class);
    }

    public List<String> getRoles(String token) throws Exception {
        String response = getUserInfo(token);

        // get roles
        Map map = new ObjectMapper().readValue(response, HashMap.class);
        return (List<String>) map.get("roles");
    }

    private String getUserInfo(String token) {
        MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();     
        headers.add("Authorization", token);

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(null, headers);
        return restTemplate.postForObject(keycloakUserInfo, request, String.class);
    }
    
    /**
     *  logging out and disabling active token from keycloak
     *
     * @param refreshToken
     */
    public String refresh(String refreshToken) throws Exception {
        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add("client_id",clientId);       
        map.add("grant_type",grantTypeRefresh);
        map.add("refresh_token",refreshToken);
        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(map, null);
       return restTemplate.postForObject(keycloakTokenUri, request, String.class);
    }
    
    
    public String createUser(CreateUserCommand command) throws Exception {
    	MultiValueMap<String, String> mapadmin = new LinkedMultiValueMap<>();
        mapadmin.add("username","admin");
        mapadmin.add("password","admin");
        mapadmin.add("client_id","admin-cli");
        mapadmin.add("grant_type",grantType);
        
    	HttpEntity<MultiValueMap<String, String>> requestadmin = new HttpEntity<>(mapadmin, new HttpHeaders());
    	AdminTokenResponse adminTokenResponse =restTemplate.postForObject(adminTokenUri, requestadmin, AdminTokenResponse.class);
    	
        MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();     
        headers.add("Authorization","Bearer "+ adminTokenResponse.getAccess_token());
        headers.add("Content-Type",MediaType.APPLICATION_JSON_VALUE);
        headers.add("Accept","*/*");
        HttpEntity<MultiValueMap<String, Object>> requestId = new HttpEntity<>( headers);

        List<Credential> credentials= new ArrayList<>();
        credentials.add(Credential.builder().type(grantType).value(command.getPassword()).temporary(false).build());
        UserInfoRequest u=UserInfoRequest.builder()
        		.username(command.getUsername())
        		.firstName(command.getFirstName())
        		.lastName(command.getLastName())
        		.credentials(credentials)
        		.enabled(true)
        		.build();
        HttpEntity<UserInfoRequest> request = new HttpEntity<>(u, headers);
        
        ResponseEntity<?> re= null;
        //crear cliente
        try {
	        re=restTemplate.exchange(userUri,HttpMethod.POST ,request, String.class);
    	}catch (Exception e) {
			log.error(e.getMessage());
			throw e;
		}        
        
        
        
        
//clien id        
        UriComponentsBuilder clientUriBuilder = UriComponentsBuilder.fromHttpUrl(clientUri).queryParam("clientId",command.getClientId());  
        String clientUrl = clientUriBuilder.toUriString();
        ResponseEntity<List<ClientResponse>>clientInfoResponse=null;
   	 	try {
   	 		clientInfoResponse =restTemplate.exchange(clientUrl,HttpMethod.GET , requestId , new ParameterizedTypeReference<List<ClientResponse>>() {});
		   	 	
		}catch (Exception e) {
			log.error(e.getMessage());
			throw e;
		}
//user-id
//   	 	Thread.sleep(1000);///hay que jugar con tiempos de admin cli token user
        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl(userUri).queryParam("username",command.getUsername());  
        String url = uriBuilder.toUriString();
   	 	
        ResponseEntity<List<UserInfoResponse>>userInfoResponse=null;
   	 	try {
		   	 	userInfoResponse =restTemplate.exchange(url,HttpMethod.GET , requestId , new ParameterizedTypeReference<List<UserInfoResponse>>() {});
		   	 	
		}catch (Exception e) {
			log.error(e.getMessage());
			throw e;
		}
//asind role   	 	
       //1.necesitamos ROLE_ID=$(curl -s "http://$KEYCLOAK_HOST_PORT/admin/realms/prototype-services/clients/$CLIENT_ID/roles" \
   	 //2. mandamos 1 como querypram y curl -i -X POST "http://$KEYCLOAK_HOST_PORT/admin/realms/prototype-services/users/$SORTEO_B_ID/role-mappings/clients/$CLIENT_ID" 
//        UriComponentsBuilder roleuriBuilder = UriComponentsBuilder.fromHttpUrl(userUri)
//        		.pathSegment(userInfoResponse.getBody().get(0).getId(),"/role-mappings/clients/",clientInfoResponse.getBody().get(0).getId())
//        		.queryParam("username",command.getUsername());  


        return re.getStatusCode().toString();
//       return restTemplate.exchange(userUri,HttpMethod.POST ,request, String.class).getBody();
    
//       {"username":"internal_a","firstName":"INTERNAL_A","lastName":"INTERNAL_A","enabled":"true","credentials":[{"type":"password","value":"123","temporary":false}]}

    }

	public String deleteUser(DeleteUserCommand command) throws BussinesRuleException {
		MultiValueMap<String, String> mapadmin = new LinkedMultiValueMap<>();
        mapadmin.add("username","admin");
        mapadmin.add("password","admin");
        mapadmin.add("client_id","admin-cli");
        mapadmin.add("grant_type",grantType);
    
    	HttpEntity<MultiValueMap<String, String>> requestadmin = new HttpEntity<>(mapadmin, new HttpHeaders());
    	AdminTokenResponse adminTokenResponse =restTemplate.postForObject(adminTokenUri, requestadmin, AdminTokenResponse.class);
  	
        MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();     
        headers.add("Authorization","Bearer "+ adminTokenResponse.getAccess_token());
        headers.add("Content-Type",MediaType.APPLICATION_JSON_VALUE);
        headers.add("Accept","*/*");

        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl(userUri).queryParam("username",command.getUsername());  
        String url = uriBuilder.toUriString();
        
        //call ge user id
//    	MultiValueMap<String, Object> idmap = new LinkedMultiValueMap<>();
//    	idmap.add("username",command.getUsername());     
//    	 HttpEntity<MultiValueMap<String, Object>> requestId = new HttpEntity<>(idmap, headers);
//    	UserInfoResponse userInfoResponse =restTemplate.getForObject(userUri, UserInfoResponse.class, requestId);
//    	UserInfoResponse userInfoResponse =restTemplate.exchange(userUri,HttpMethod.GET ,requestId, UserInfoResponse.class).getBody();
   	 	HttpEntity<MultiValueMap<String, Object>> requestId = new HttpEntity<>( headers);
   	 	
//   	 	String u =restTemplate.exchange(url,HttpMethod.GET , requestId , String.class).getBody().toString();
   	 	ResponseEntity<List<UserInfoResponse>>userInfoResponse=null;
   	 	try {
		   	 	userInfoResponse =restTemplate.exchange(url,HttpMethod.GET , requestId , new ParameterizedTypeReference<List<UserInfoResponse>>() {});
		   	 	
		}catch (Exception e) {
			log.error(e.getMessage());
			throw e;
		}
//   	 	UserInfoResponse userInfoResponse =restTemplate.exchange(url,HttpMethod.GET , requestId , UserInfoResponse.class).getBody();
   	 	if(userInfoResponse.getBody().isEmpty())
   	 		throw new BussinesRuleException(HttpStatus.NOT_FOUND.name(), "no user found" ,HttpStatus.NOT_FOUND);   

    	//call delete by id
    	MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add("id",userInfoResponse.getBody().get(0).getId());       

        HttpEntity<MultiValueMap<String, Object>> request = new HttpEntity<>(headers);
        uriBuilder=UriComponentsBuilder.fromHttpUrl(userUri).path(userInfoResponse.getBody().get(0).getId());
        String a=uriBuilder.buildAndExpand(map).toUriString();
        
        return restTemplate.exchange(uriBuilder.toUriString(),HttpMethod.DELETE ,request, String.class).getStatusCode().toString();

//        return restTemplate.exchange(uriBuilder.toUriString(),HttpMethod.DELETE ,request, String.class).getBody();

//        return restTemplate.exchange(uriBuilder.toUriString(),HttpMethod.DELETE ,request, String.class).getStatusCode().equals(HttpStatus.NO_CONTENT)?"delete":"undelete";
      
//        restTemplate.exchange(userUri,HttpMethod.DELETE ,request, String.class);
//       return restTemplate.exchange(userUri,HttpMethod.DELETE ,request, String.class).getStatusCode().equals(HttpStatus.NO_CONTENT)?"delete":"undelete";
	}

}

