package com.aj.snowpark.utils;

import com.snowflake.snowpark_java.Session;
import net.snowflake.client.jdbc.internal.javax.annotation.PostConstruct;

import java.net.URISyntaxException;

public class SnowSession {

    private static Session session;

    public static synchronized Session getSession() throws URISyntaxException {
        if (session == null) {
            setSession();
        }
        return session;
    }

    private static void setSession() throws URISyntaxException {
        session = Session.builder()
                .configFile(SnowSession.class.getResource("/application.properties").toURI().getPath())
                .create();
    }

}


