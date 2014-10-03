package no.arhplanet.game;

import com.google.inject.Inject;
import com.google.inject.Singleton;

@Singleton
public class Service {

    public static final String SERVICE_STRING = "SERVICE_STRING";

    public Service() {
    }

    @Inject
    public String get() {

        return SERVICE_STRING;
    }
}


