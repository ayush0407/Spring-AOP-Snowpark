package com.aj.snowpark.utils;


import java.net.URISyntaxException;

public class SnowUtil extends SnowSession {


    public SnowUtil() throws URISyntaxException {
    }

    public boolean checkIfStreamIsEmpty() {
        String streamName = "bank_stream";
        String schemaName = "snowpark";
        String query = String.format("select system$stream_has_data('%s.%s')", schemaName, streamName);

        // Get the first row from the result set
        boolean hasData = session.sql(query).collect()[0].getBoolean(0);

        // Return the opposite of hasData to indicate whether the stream is empty
        return !hasData;
    }

    public boolean consumeStream() {
        String streamName = "bank_stream";
        String schemaName = "snowpark";
        String createTableQuery = String.format("create or replace temporary table temp_%s as select * from %s.%s", streamName, schemaName, streamName);
        session.sql(createTableQuery).collect();
        session.sql("commit");
        return checkIfStreamIsEmpty();
    }


}
