
package no.arhplanet.game;

import java.io.IOException;
import java.net.URI;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.DispatcherType;
import javax.ws.rs.core.UriBuilder;

import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.grizzly.servlet.FilterRegistration;
import org.glassfish.grizzly.servlet.ServletRegistration;
import org.glassfish.grizzly.servlet.WebappContext;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.grizzly2.servlet.GrizzlyWebContainerFactory;
import org.glassfish.jersey.servlet.ServletContainer;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.servlet.GuiceFilter;

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

        final HttpServer serverLocal = GrizzlyHttpServerFactory.createHttpServer(BASE_URI, false);

// Create Web application context
        final WebappContext context = new WebappContext("Guice Webapp sample", "");

        context.addListener(InjectorHolder.class);

// Initialize and register Jersey ServletContainer
        final ServletRegistration servletRegistration =
                context.addServlet("ServletContainer", ServletContainer.class);
        servletRegistration.addMapping("/*");
        servletRegistration.setInitParameter("javax.ws.rs.Application",
                                             "no.arhplanet.game.Application");

// Initialize and register GuiceFilter
        final FilterRegistration registration =
                context.addFilter("GuiceFilter", GuiceFilter.class);
        registration.addMappingForUrlPatterns(EnumSet.allOf(DispatcherType.class), "/*");

        context.deploy(serverLocal);

        serverLocal.start();
        return serverLocal;

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
