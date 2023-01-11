package com.example;

import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
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