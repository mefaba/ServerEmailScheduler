package org.emailreportmanager;

import org.emailreportmanager.frontend.WebApp;
import org.emailreportmanager.services.H2ConsoleStarter;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        // START SQL SERVER
        H2ConsoleStarter h2ConsoleStarter = new H2ConsoleStarter();
        h2ConsoleStarter.startServer();

        // Start WEB APP
        WebApp webApp = new WebApp();


    }
}