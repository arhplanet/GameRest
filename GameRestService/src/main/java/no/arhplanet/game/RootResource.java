package no.arhplanet.game;

import java.net.URI;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;

import org.glassfish.jersey.client.oauth2.ClientIdentifier;

@Path("/")
public class RootResource {

    String consumerKey = "296584509990-kpkn5h16ckhn9ntudu6vrvk40kpde77a.apps.googleusercontent.com";
    String consumerSecret = "813aHcCvbG2INLjPk0Thdrwt";

    public RootResource() {
        SimpleOAuthService.setClientIdentifier(new ClientIdentifier(consumerKey, consumerSecret));
    }

    @Context
    private UriInfo uriInfo;

    @GET
    @Produces("text/plain")
    public Response getRoot() {
        final URI uri = UriBuilder.fromUri(uriInfo.getBaseUri()).path("myresource").build();
        return Response.seeOther(uri).build();
    }

}
