package org.emailreportmanager.controllers;

import io.javalin.http.Context;

import java.io.IOException;

public class WebController {
    public static void handlerComponentTable (Context ctx) throws IOException {
        ctx.render("templates/component_table.peb");
    }
}
