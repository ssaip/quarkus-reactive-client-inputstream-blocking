package com.example;

import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import java.io.InputStream;

@RegisterRestClient
@Path("/v1")
public interface ApiWithBlockingResponseExceptionMapper {

    @POST
    @Consumes({"application/json"})
    @Produces({"application/json"})
    InputStream asInputStreamThrowsBlockingNotAllowedException();

    @POST
    @Consumes({"application/json"})
    @Produces({"application/json"})
    String asStringWorksFine();

}