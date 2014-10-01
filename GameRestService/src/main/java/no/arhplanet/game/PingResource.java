
package no.arhplanet.game;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

// The Java class will be hosted at the URI path "/myresource"
@Path("/pingresource")
public class PingResource {

    @GET
    @Produces("text/plain")
    public Response getIt() {
        return Response.ok("error").build();
    }
}
