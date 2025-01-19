package com.netflix.api.netflix.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @PostMapping("/create-role/{roleName}/{password}")
    public String createRole(@PathVariable String roleName, @PathVariable String password) {
        try {
            // Create the PostgreSQL user with the specified password
            String createUserSql = "CREATE USER " + roleName + " WITH PASSWORD ?";
            jdbcTemplate.update(createUserSql, password);  // Run the CREATE USER query

            // Optionally, grant privileges to the user (adjust as necessary)
            String grantPrivilegesSql = "GRANT ALL PRIVILEGES ON DATABASE netflix_api TO " + roleName;
            jdbcTemplate.update(grantPrivilegesSql);  // Grant privileges to the new user

            return "User '" + roleName + "' created and granted privileges successfully!";
        } catch (Exception e) {
            e.printStackTrace();
            return "Error occurred while creating the user: " + e.getMessage();
        }
    }
}
