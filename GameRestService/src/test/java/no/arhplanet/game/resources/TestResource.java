package no.arhplanet.game.resources;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

@Path("/testresource")
public class TestResource {

    @GET
    @Produces("text/plain")
    @Path("getit")
    public String getIt() {
        return "jubi";
    }
}
