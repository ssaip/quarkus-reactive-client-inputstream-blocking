package com.example;

import org.eclipse.microprofile.rest.client.RestClientBuilder;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.UriBuilder;

@Path("/hello")
@ApplicationScoped
public class ExampleResource {

    private final BlockingResponseExceptionMapper blockingResponseExceptionMapper;

    public ExampleResource(BlockingResponseExceptionMapper blockingResponseExceptionMapper) {
        this.blockingResponseExceptionMapper = blockingResponseExceptionMapper;
    }

    @GET
    @Path("/string")
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
        var client = RestClientBuilder.newBuilder()
                .baseUri(UriBuilder.fromUri("http://localhost:5000").build())
                .register(blockingResponseExceptionMapper)
                .build(ApiWithBlockingResponseExceptionMapper.class);

        client.asStringWorksFine();

        return "Hello from RESTEasy Reactive";
    }

    @GET
    @Path("/inputStream")
    @Produces(MediaType.TEXT_PLAIN)
    public String hello2() {
        var client = RestClientBuilder.newBuilder()
                .baseUri(UriBuilder.fromUri("http://localhost:5000").build())
                .register(blockingResponseExceptionMapper)
                .build(ApiWithBlockingResponseExceptionMapper.class);

        client.asInputStreamThrowsBlockingNotAllowedException();

        return "Hello from RESTEasy Reactive";
    }
}