package com.example;

import org.eclipse.microprofile.rest.client.RestClientBuilder;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriBuilder;

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