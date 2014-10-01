
package no.arhplanet.game;

import java.net.URI;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.client.Client;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;

import org.glassfish.jersey.client.oauth2.OAuth2ClientSupport;
import org.glassfish.jersey.client.oauth2.OAuth2CodeGrantFlow;
import org.glassfish.jersey.client.oauth2.OAuth2FlowGoogleBuilder;
import org.glassfish.jersey.client.oauth2.OAuth2Parameters;

// The Java class will be hosted at the URI path "/myresource"
@Path("/myresource")
public class MyResource {

    @Context
    private UriInfo uriInfo;

    @GET
    @Produces("text/plain")
    public Response getIt(@QueryParam("access_token") String token, @QueryParam("expires_in") String expires) {

        if (token == null) {
            return AuthorizeResource.doAuthorize(uriInfo);
        }

        return Response.ok("jubi").build();
    }
}
