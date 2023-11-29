package org.emailreportmanager.controllers;

import io.javalin.http.Context;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class WebPageController {
    public void indexPage (Context ctx) throws IOException {
        Map<String, String> templateVariables = new HashMap<>();
        templateVariables.put("message", "Tanri Pseeble!");
        ctx.render("templates/index.peb", templateVariables);
    }
}
