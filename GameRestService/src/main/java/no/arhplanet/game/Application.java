package no.arhplanet.game;

import javax.inject.Inject;

import org.glassfish.hk2.api.ServiceLocator;
import org.glassfish.jersey.server.ResourceConfig;
import org.jvnet.hk2.guice.bridge.api.GuiceBridge;
import org.jvnet.hk2.guice.bridge.api.GuiceIntoHK2Bridge;

public class Application extends ResourceConfig {

    @Inject
    public Application(ServiceLocator serviceLocator) {
        // Set package to look for resources in
        packages("no.arhplanet.game");

        System.out.println("Registering injectables...");
        GuiceBridge.getGuiceBridge().initializeGuiceBridge(serviceLocator);

        GuiceIntoHK2Bridge guiceBridge = serviceLocator.getService(GuiceIntoHK2Bridge.class);
        guiceBridge.bridgeGuiceInjector(InjectorHolder.injector);

    }
}
