package com.archetype.architectural.order.config;

import org.springframework.context.annotation.Bean;
import io.opentelemetry.contrib.sampler.RuleBasedRoutingSampler;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

import io.opentelemetry.api.trace.SpanKind;
import io.opentelemetry.sdk.autoconfigure.spi.AutoConfigurationCustomizerProvider;
import io.opentelemetry.semconv.UrlAttributes;

@Configuration
@Order(Integer.MIN_VALUE)
public class OtelWebFilter {

	 @Bean
	  public AutoConfigurationCustomizerProvider otelCustomizer() {
	    return p ->
	        p.addSamplerCustomizer(
	            (fallback, config) ->
	                RuleBasedRoutingSampler.builder(SpanKind.SERVER, fallback)
	                    .drop(UrlAttributes.URL_PATH, "^/actuator")
	                    .build());
	  }
}
