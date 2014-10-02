package no.arhplanet.game;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.servlet.GuiceServletContextListener;
import com.google.inject.servlet.ServletModule;
import com.sun.jersey.api.core.PackagesResourceConfig;
import com.sun.jersey.api.core.ResourceConfig;
import com.sun.jersey.guice.spi.container.servlet.GuiceContainer;

import no.arhplanet.game.dao.Dao;
import no.arhplanet.game.dao.PlayerDaoImpl;

public class Application extends GuiceServletContextListener {

    @Override
    protected Injector getInjector() {
        return Guice.createInjector( new ServletModule() {
            @Override
            protected void configureServlets() {
                bind(Dao.class).to(PlayerDaoImpl.class);

                ResourceConfig rc = new PackagesResourceConfig( "no.arhplanet.game.resources" );
                for ( Class<?> resource : rc.getClasses() ) {
                    bind( resource );
                }

                serve( "/services/*" ).with( GuiceContainer.class );
            }
        } );
    }
}
