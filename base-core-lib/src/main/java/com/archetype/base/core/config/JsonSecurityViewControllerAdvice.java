/**
 * Ayesa
 * @author jcallejo
 */

package com.archetype.base.core.config;

import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.AbstractMappingJacksonResponseBodyAdvice;
// TODO: Auto-generated Javadoc

/**
 * The Class JsonSecurityViewControllerAdvice.
 *
 * @author javier
 */
@RestControllerAdvice
public class JsonSecurityViewControllerAdvice extends AbstractMappingJacksonResponseBodyAdvice {

	/**
	 * Before body write internal.
	 *
	 * @param bodyContainer the body container
	 * @param contentType the content type
	 * @param returnType the return type
	 * @param request the request
	 * @param response the response
	 */
	@Override
	protected void beforeBodyWriteInternal(final MappingJacksonValue bodyContainer, final MediaType contentType,
			final MethodParameter returnType, final ServerHttpRequest request,
			final ServerHttpResponse response) {

//		if (SecurityContextHolder.getContext().getAuthentication() != null
//				&& SecurityContextHolder.getContext().getAuthentication().getAuthorities() != null) {
//
//			Collection<? extends GrantedAuthority> authorities =
//					SecurityContextHolder.getContext().getAuthentication().getAuthorities();
//			List<String> accessList = authorities.stream().map(GrantedAuthority::getAuthority)
//					.filter(ga -> ga.equals(BaseRoles.ROLE_ANONYMOUS)).collect(Collectors.toList());
//
//			if (accessList.contains(BaseRoles.ROLE_ANONYMOUS)) {
//				bodyContainer.setSerializationView(BaseView.BaseUser.class);
//			}
//		}

	}

}
