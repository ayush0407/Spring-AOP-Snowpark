package com.aj.snowpark.service;

import com.aj.snowpark.utils.SnowSession;
import com.aj.snowpark.utils.SnowUtil;
import com.snowflake.snowpark_java.DataFrame;

import java.net.URISyntaxException;

public class SnowService extends SnowSession {

    private SnowUtil snowUtil;

    public SnowService() throws URISyntaxException {
        super();
        snowUtil = new SnowUtil();
    }

    public long fetchStreamData() throws URISyntaxException {
        try {
            if (snowUtil.checkIfStreamIsEmpty()) {
                System.out.println("The stream is empty exiting the method.");
                return 0L;
            } else {
                DataFrame df = SnowSession.getSession().table("bank_stream");
                System.out.println(df.count());
                return df.count();
            }
        } finally {
            System.out.println("The stream has been consumed + " + snowUtil.consumeStream());
        }

    }

    public static void main(String[] args) throws URISyntaxException {
        SnowService snowService = new SnowService();
        System.out.println(snowService.fetchStreamData());
    }
}
