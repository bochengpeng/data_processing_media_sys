package org.example.moviesystem.login;

import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Component;

@Component
public class DataSourceRouter
{

    public DriverManagerDataSource getDataSource(String username)
    {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("org.postgresql.Driver");
        dataSource.setUrl("jdbc:postgresql://localhost:5432/mediasys");

        return dataSource;
    }
}


