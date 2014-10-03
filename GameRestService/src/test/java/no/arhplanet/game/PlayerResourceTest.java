package no.arhplanet.game;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;

import javax.ws.rs.core.MediaType;

import org.junit.Test;

import no.arhplanet.game.models.Player;

public class PlayerResourceTest extends MainTest {


    /**
     * RootResource to see that the message "Got it!" is sent in the response.
     */
    @Test
    public void testPlayerGet() {
        Player value = r.path("/player/info/").request().get().readEntity(Player.class);
        assertNotNull("Skal ikke være null", value);
    }

}
