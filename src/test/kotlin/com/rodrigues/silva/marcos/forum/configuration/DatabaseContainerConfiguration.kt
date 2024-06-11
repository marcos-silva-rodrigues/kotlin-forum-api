package com.rodrigues.silva.marcos.forum.configuration

import com.redis.testcontainers.RedisContainer
import org.springframework.test.context.DynamicPropertyRegistry
import org.springframework.test.context.DynamicPropertySource
import org.testcontainers.containers.GenericContainer
import org.testcontainers.containers.MySQLContainer
import org.testcontainers.junit.jupiter.Container
import org.testcontainers.utility.DockerImageName


abstract class DatabaseContainerConfiguration {

    companion object {
        @Container
        private val mysqlContainer = MySQLContainer<Nothing>("mysql:latest").apply {
            withDatabaseName("testdb")
            withUsername("joao")
            withPassword("12345")
            withReuse(true)
        }

        @JvmStatic
        @Container
        protected val redisContainer = GenericContainer<Nothing>("redis:latest").apply {
            withExposedPorts(6379)
            withAccessToHost(true)
        }

        @JvmStatic
        @DynamicPropertySource
        fun properties(registry: DynamicPropertyRegistry) {
            registry.add("spring.datasource.url", mysqlContainer::getJdbcUrl);
            registry.add("spring.datasource.password", mysqlContainer::getPassword);
            registry.add("spring.datasource.username", mysqlContainer::getUsername);
            registry.add("spring.redis.host", {"localhost"})
            registry.add("spring.redis.port", { redisContainer.getMappedPort(6379).toString() })
        }
    }
}