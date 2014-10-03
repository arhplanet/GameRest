
package no.arhplanet.game;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;

import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.client.JerseyClientBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;


public class MainTest {

    public HttpServer httpServer;

    public WebTarget r;

   @Before
    public void setUp() throws Exception {
        //start the Grizzly2 web container
        httpServer = Main.startServer();
        // create the client
        Client client = new JerseyClientBuilder().build();
        r = client.target(no.arhplanet.game.Main.BASE_URI);
    }

    @After
    public void tearDown() throws Exception {
        httpServer.stop();
    }

    @Test
    public void _testMyResource2() {
        Response response = r.path("/testresource/getit/").request().get();
        assertEquals(200, response.getStatus());
    }

    /**
     * RootResource if a WADL document is available at the relative path
     * "application.wadl".
     */
    @Test
    public void testApplicationWadl() {
        //r.request().accept(MediaTypes.WADL);
        String serviceWadl = r.path("/application.wadl").getUri().toString();
        assertTrue(serviceWadl.length() > 0);
    }
}
