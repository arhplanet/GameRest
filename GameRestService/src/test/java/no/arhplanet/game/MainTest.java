
package no.arhplanet.game;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;

import javax.ws.rs.core.Response;

import org.glassfish.grizzly.http.server.HttpServer;
import org.jboss.resteasy.client.jaxrs.ResteasyClient;
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.jboss.resteasy.client.jaxrs.ResteasyWebTarget;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;


public class MainTest {

    private HttpServer httpServer;
    
    protected ResteasyWebTarget r;

   @Before
    public void setUp() throws Exception {
        //start the Grizzly2 web container
        httpServer = Main.startServer();
        // create the client
        ResteasyClient client = new ResteasyClientBuilder().build();
        r = client.target(Main.BASE_URI);
        r.register(new Application());
    }

    @After
    public void tearDown() throws Exception {
        httpServer.stop();
    }



    @Test
    public void _testMyResource2() {
        Response response = r.path("/services/testresource/getit/").request().get();
        assertEquals(200, response.getStatus());
    }

    /**
     * RootResource if a WADL document is available at the relative path
     * "application.wadl".
     */
    @Test
    public void testApplicationWadl() {
        //r.request().accept(MediaTypes.WADL);
        String serviceWadl = r.path("/services/application.wadl").getUri().toString();
        assertTrue(serviceWadl.length() > 0);
    }
}
