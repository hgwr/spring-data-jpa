/*
 * Copyright 2024-present the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.springframework.data.jpa.repository;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.sample.User;
import org.springframework.data.jpa.repository.sample.UserRepository;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

/**
 * EclipseLink integration tests for positional parameter binding on derived queries.
 *
 * @author Spring Data Team
 */
@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = { "classpath:config/namespace-application-context-h2.xml",
		"classpath:eclipselink-h2.xml" })
@Transactional
class EclipseLinkDerivedQueryParameterBindingIntegrationTests {

	@Autowired UserRepository userRepository;

	@Test
	void bindsPositionalParametersForDerivedQuery() {

		User user = new User("active", "user", "active@spring.io");
		user = userRepository.saveAndFlush(user);

		assertThat(userRepository.findByIdAndActive(user.getId(), true)).contains(user);
	}
}
