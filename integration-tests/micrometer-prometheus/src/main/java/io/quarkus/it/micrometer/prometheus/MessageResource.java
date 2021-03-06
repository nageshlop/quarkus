package io.quarkus.it.micrometer.prometheus;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import io.micrometer.core.instrument.MeterRegistry;

@Path("/message")
public class MessageResource {

    private final MeterRegistry registry;

    public MessageResource(MeterRegistry registry) {
        this.registry = registry;
    }

    @GET
    public String message() {
        return registry.getClass().getName();
    }

    @GET
    @Path("fail")
    public String fail() {
        throw new NullPointerException("Failed on purpose");
    }

    @GET
    @Path("item/{id}")
    public String item(@PathParam("id") String id) {
        return "return message with id " + id;
    }

    @GET
    @Path("match/{id}/{sub}")
    public String match(@PathParam("id") String id, @PathParam("sub") String sub) {
        return "return message with id " + id + ", and sub " + sub;
    }

    @GET
    @Path("match/{text}")
    public String optional(@PathParam("text") String text) {
        return "return message with text " + text;
    }
}
