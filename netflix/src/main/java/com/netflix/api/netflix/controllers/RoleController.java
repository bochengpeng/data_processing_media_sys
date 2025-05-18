package com.netflix.api.netflix.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/role")
public class RoleController
{
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @PostMapping("/create-role/{roleName}/{password}")
    public String createRole(@PathVariable String roleName, @PathVariable String password)
    {
        try
        {
            // Escape single quotes in password to prevent SQL injection
            String escapedPassword = password.replace("'", "''");

            // Build SQL string safely
            String createUserSql = "CREATE USER \"" + roleName + "\" WITH PASSWORD '" + escapedPassword + "'";
            this.jdbcTemplate.execute(createUserSql);

            // Grant privileges
            String grantPrivilegesSql = "GRANT ALL PRIVILEGES ON DATABASE resit TO \"" + roleName + "\"";
            this.jdbcTemplate.execute(grantPrivilegesSql);

            return "User '" + roleName + "' created and granted privileges successfully!";
        } catch (Exception e)
        {
            e.printStackTrace();
            return "Error occurred while creating the user: " + e.getMessage();
        }
    }
}
