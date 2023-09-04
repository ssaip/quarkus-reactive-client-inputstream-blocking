package com.example;

import com.fasterxml.jackson.databind.JsonNode;
import io.smallrye.common.annotation.Blocking;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.core.MultivaluedMap;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.rest.client.ext.ResponseExceptionMapper;

@ApplicationScoped
@Blocking
public class BlockingResponseExceptionMapper implements ResponseExceptionMapper<Exception> {

    @Override
    @Blocking
    public Exception toThrowable(Response response) {
        var body = response.readEntity(JsonNode.class);
        System.out.println(body);
        return null;
    }

    @Override
    public boolean handles(int status, MultivaluedMap<String, Object> headers) {
        return true;
    }
}
