package org.example.moviesystem.login;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class MediaService
{

    @Autowired
    private DataSourceRouter dataSourceRouter;

    public JdbcTemplate getJdbcTemplate(String username)
    {
        return new JdbcTemplate(dataSourceRouter.getDataSource(username));
    }

    public void performDatabaseOperation(String username)
    {
        JdbcTemplate jdbcTemplate = getJdbcTemplate(username);
        // Use jdbcTemplate to perform database operations
    }
}
