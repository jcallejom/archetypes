package com.archetype.app.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import com.archetype.base.core.aop.log.LogAspect;

@Configuration
@EnableAspectJAutoProxy
public class LogginAspectConf {
	
	@Bean
	public LogAspect logAspect() {
		return new LogAspect();
	}
}
