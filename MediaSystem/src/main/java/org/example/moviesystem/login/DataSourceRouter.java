package org.example.moviesystem.login;

import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Component;

@Component
public class DataSourceRouter {

    public DriverManagerDataSource getDataSource(String username) {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("org.postgresql.Driver");
        dataSource.setUrl("jdbc:postgresql://localhost:5432/mediasys");

        return dataSource;
    }
}

//        switch (username.toLowerCase()) {
//            case "junior":
//                dataSource.setUsername("junior");
//                dataSource.setPassword("junior125");
//                break;
//            case "medior":
//                dataSource.setUsername("medior");
//                dataSource.setPassword("medior123");
//                break;
//            case "senior":
//                dataSource.setUsername("senior");
//                dataSource.setPassword("senior123");
//                break;
//            case "apiuser":
//                dataSource.setUsername("apiuser");
//                dataSource.setPassword("api123");
//                break;
//            default:
//                throw new RuntimeException("Unknown user: " + username);
//        }

