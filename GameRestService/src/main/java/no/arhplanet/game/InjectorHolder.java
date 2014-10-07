package no.arhplanet.game;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.servlet.GuiceServletContextListener;
import com.google.inject.servlet.ServletModule;

import no.arhplanet.game.dao.Dao;
import no.arhplanet.game.dao.PlayerDaoImpl;
import no.arhplanet.game.resources.PlayerResource;

public class InjectorHolder extends GuiceServletContextListener {

    public static Injector injector;

    @Override
    protected Injector getInjector() {
        System.out.println("Getting injector");
        injector = Guice.createInjector(new ServletModule() {
            // Configure your IOC
            @Override
            protected void configureServlets() {
                bind(Service.class);
                bind(Dao.class).to(PlayerDaoImpl.class);
                bind(PlayerResource.class);
            }
        });

        return injector;

    }


}

