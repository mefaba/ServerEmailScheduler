package org.emailreportmanager;

import io.javalin.Javalin;
import io.javalin.http.Context;
import io.javalin.http.Handler;
import io.javalin.plugin.rendering.template.JavalinPebble;

import org.emailreportmanager.controllers.EmailController;
import org.emailreportmanager.controllers.TableController;
import org.emailreportmanager.controllers.WebPageController;
import org.jetbrains.annotations.NotNull;

import com.mitchellbosecke.pebble.PebbleEngine;

import org.emailreportmanager.services.H2ConsoleStarter;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        // START SQL SERVER
        H2ConsoleStarter h2ConsoleStarter = new H2ConsoleStarter();
        h2ConsoleStarter.startServer();

        // Start template emgine
        PebbleEngine engine = new PebbleEngine.Builder().cacheActive(false).build();
        JavalinPebble.configure(engine);

        // SETUP APP SERVER
        Javalin app = Javalin.create();

        // page handlers
        WebPageController webPageController = new WebPageController();
        app.get("/", new Handler() {
            @Override
            public void handle(@NotNull Context context) throws Exception {
                webPageController.indexPage(context);
            }
        });

        /* *** data handlers  *** */

        //table
        TableController tableController = new TableController();
        app.post("/data", tableController::onSubmitTable);
        app.get("/table", tableController::htmxTable);

        EmailController emailController = new EmailController();
        app.post("/test-email", emailController::handleEmailSubmit);

        // website handlers


        // START APP SERVER
        app.start(7070);

    }
}