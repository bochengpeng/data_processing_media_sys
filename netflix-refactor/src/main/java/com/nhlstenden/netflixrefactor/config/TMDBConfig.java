package com.nhlstenden.netflixrefactor.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
public class TMDBConfig
{

    @Autowired
    private Environment environment;

    public String getApiKey()
    {
        return this.environment.getProperty("tmdb.api.key");
    }
}

