package com.netflix.api.netflix;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class DatabaseConnectionTest {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Test
    public void testDatabaseConnection() {
        // Simple query to check if the connection works
        String result = jdbcTemplate.queryForObject("SELECT version();", String.class);

        // Assert that the result is not null and contains database version info
        assertThat(result).isNotNull();
        System.out.println("Database Version day ne: " + result);
    }
}
