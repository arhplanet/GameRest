
package no.arhplanet.game;

import java.io.IOException;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.core.UriBuilder;

import org.glassfish.grizzly.http.server.HttpServer;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.servlet.ServletModule;
import com.sun.jersey.api.container.grizzly2.GrizzlyServerFactory;
import com.sun.jersey.api.core.PackagesResourceConfig;
import com.sun.jersey.core.spi.component.ioc.IoCComponentProviderFactory;
import com.sun.jersey.guice.spi.container.GuiceComponentProviderFactory;
import com.sun.jersey.guice.spi.container.servlet.GuiceContainer;

import no.arhplanet.game.dao.Dao;
import no.arhplanet.game.dao.PlayerDaoImpl;

public class Main {

    private static int getPort(int defaultPort) {
        //grab port from environment, otherwise fall back to default port 9998
        String httpPort = System.getProperty("jersey.test.port");
        if (null != httpPort) {
            try {
                return Integer.parseInt(httpPort);
            } catch (NumberFormatException e) {
            }
        }
        return defaultPort;
    }

    private static URI getBaseURI() {
        return UriBuilder.fromUri("http://localhost/").port(getPort(9998)).build();
    }

    public static final URI BASE_URI = getBaseURI();

    protected static HttpServer startServer() throws IOException {

        PackagesResourceConfig rc = new PackagesResourceConfig( "no.arhplanet.game.resources" );

        Injector injector = Guice.createInjector(new ServletModule() {
            @Override
            protected void configureServlets() {
                bind(Dao.class).to(PlayerDaoImpl.class);

                com.sun.jersey.api.core.ResourceConfig rc = new PackagesResourceConfig("no.arhplanet.game.resources");
                for (Class<?> resource : rc.getClasses()) {
                    bind(resource);
                }

                Map<String, String> initParams = new HashMap<String, String>();
                initParams.put("com.sun.jersey.config.feature.Trace", "true");
                initParams.put("com.sun.jersey.api.json.POJOMappingFeature", "true");

                serve("/services/*").with(GuiceContainer.class, initParams);
            }
        });

        IoCComponentProviderFactory ioc = new GuiceComponentProviderFactory( rc, injector );
        return GrizzlyServerFactory.createHttpServer(BASE_URI + "services/", rc, ioc);

    }
    
    public static void main(String[] args) throws IOException {
        // Grizzly 2 initialization
        HttpServer httpServer = startServer();
        System.out.println(String.format("Jersey app started with WADL available at "
                + "%sapplication.wadl\nHit enter to stop it...",
                BASE_URI));
        System.in.read();
        httpServer.stop();
    }    
}
