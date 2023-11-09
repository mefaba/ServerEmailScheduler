package org.webdemo;

import io.javalin.Javalin;
import io.javalin.http.Context;
import io.javalin.http.Handler;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
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

        app.start(7070);
    }
}