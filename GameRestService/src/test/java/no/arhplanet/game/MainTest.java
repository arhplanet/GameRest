
package no.arhplanet.game;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;

import org.glassfish.grizzly.http.server.HttpServer;


import junit.framework.TestCase;


public class MainTest extends TestCase {

    private HttpServer httpServer;
    
    private WebTarget r;

    public MainTest(String testName) {
        super(testName);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        
        //start the Grizzly2 web container 
        httpServer = Main.startServer();

        // create the client
        Client client = ClientBuilder.newClient();
        r = client.target(Main.BASE_URI);
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
        httpServer.stop();
    }

    /**
     * RootResource to see that the message "Got it!" is sent in the response.
     */
    public void _testMyResource() {


        String responseMsg = r.path("myresource").toString();
        assertEquals("Got it!", responseMsg);
    }

    /**
     * RootResource if a WADL document is available at the relative path
     * "application.wadl".
     */
    public void testApplicationWadl() {
        //r.request().accept(MediaTypes.WADL);
        String serviceWadl = r.path("application.wadl").getUri().toString();
                
        assertTrue(serviceWadl.length() > 0);
    }
}
