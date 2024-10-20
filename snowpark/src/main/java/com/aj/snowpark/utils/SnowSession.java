package com.aj.snowpark.utils;

import com.snowflake.snowpark_java.Session;
import net.snowflake.client.jdbc.internal.javax.annotation.PostConstruct;

import java.net.URISyntaxException;

public class SnowSession {

    protected Session session;

    public SnowSession() throws URISyntaxException {
        this.setSession();
    }

    public void setSession() throws URISyntaxException {
        session = Session.builder()
                .configFile(SnowSession.class.getResource("/application.properties").toURI().getPath())
                .create();
    }

    public Session getSession() {
        return session;
    }
}

