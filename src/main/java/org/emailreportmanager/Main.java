package org.emailreportmanager;

import io.javalin.Javalin;
import io.javalin.http.Context;
import io.javalin.http.Handler;
import io.javalin.plugin.rendering.template.JavalinPebble;

import org.emailreportmanager.controllers.DataController;
import org.emailreportmanager.controllers.WebController;
import org.jetbrains.annotations.NotNull;

import com.mitchellbosecke.pebble.PebbleEngine;

import org.emailreportmanager.services.H2ConsoleStarter;

import java.util.HashMap;
import java.util.Map;

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
        app.get("/hello", ctx -> ctx.result("Hello World"));
        app.get("/", new Handler() {
            @Override
            public void handle(@NotNull Context context) throws Exception {
                Map<String, String> templateVariables = new HashMap<>();
                templateVariables.put("message", "Tanri Pseeble!");
                context.render("templates/index.peb", templateVariables);
            }
        });

        // data handlers
        DataController dataController = new DataController();
        app.post("/data", ctx -> {
            dataController.handleTableData(ctx);
        });
        app.post("/test-email", ctx -> {
            dataController.handleEmailData(ctx);
        });

        // website handlers
        app.get("/component-table", WebController::handlerComponentTable);

        // START APP SERVER
        app.start(7070);

    }
}