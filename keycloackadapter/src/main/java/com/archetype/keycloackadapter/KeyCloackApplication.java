/*
 * Copyright 2012-2013 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.archetype.keycloackadapter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;

import com.archetype.base.core.audit.config.AuditHistoryConfiguration;


@SpringBootApplication
//@ComponentScan( basePackages = {"com.archetype"},
//excludeFilters = {@ComponentScan.Filter(
//type = FilterType.ASSIGNABLE_TYPE , classes = {AuditHistoryConfiguration.class}) })
//@EntityScan(basePackages = {"es.once.newsop.base.core.audit.model","es.once.newsop.example.infrastructure.out.db.jpa.entity"})
public class KeyCloackApplication  {


	public static void main(String[] args)  {
		SpringApplication.run(KeyCloackApplication.class, args);
	}
}
