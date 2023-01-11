package com.example;

import com.fasterxml.jackson.databind.JsonNode;
import org.eclipse.microprofile.rest.client.ext.ResponseExceptionMapper;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;

@ApplicationScoped
public class BlockingResponseExceptionMapper implements ResponseExceptionMapper<Exception> {

    @Override
    public Exception toThrowable(Response response) {

        try {
            // notice how this is perfectly fine and does not throw an exception because we have @Blocking
            // with @NonBlocking this would already throw
            Thread.sleep(500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        var body = response.readEntity(JsonNode.class);
        System.out.println(body);
        return null;
    }

    @Override
    public boolean handles(int status, MultivaluedMap<String, Object> headers) {
        return true;
    }
}
