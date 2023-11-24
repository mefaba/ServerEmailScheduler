package org.emailreportmanager;

import io.javalin.Javalin;
import io.javalin.http.Context;
import io.javalin.http.Handler;
import org.emailreportmanager.controllers.DataController;
import org.jetbrains.annotations.NotNull;
import org.emailreportmanager.services.H2ConsoleStarter;

import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        //START SQL SERVER
        H2ConsoleStarter h2ConsoleStarter = new H2ConsoleStarter();
        h2ConsoleStarter.startServer();

        //SETUP APP SERVER
        Javalin app = Javalin.create();
        app.get("/hello", ctx -> ctx.result("Hello World"));
        app.get("/", new Handler() {
            @Override
            public void handle(@NotNull Context context) throws Exception {
                Map<String,String> templateVariables = new HashMap<>();
                templateVariables.put("message", "Tanri Peeble!");
                context.render("templates/index.peb",templateVariables);
            }
        });

        // Define your routes and controllers here
        app.post("/data", DataController::handlePostRequest);




        //START APP SERVER
        app.start(7070);
    }
}