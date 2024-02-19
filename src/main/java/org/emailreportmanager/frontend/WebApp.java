package org.emailreportmanager.frontend;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.javalin.http.staticfiles.Location;
import org.emailreportmanager.frontend.models.Scheduler;
import org.emailreportmanager.frontend.models.Tool;

import com.mitchellbosecke.pebble.PebbleEngine;
import com.mitchellbosecke.pebble.loader.FileLoader;
import io.javalin.Javalin;
import io.javalin.http.Context;
import io.javalin.plugin.rendering.JavalinRenderer;
import io.javalin.plugin.rendering.template.JavalinPebble;
import java.util.ArrayList;

public class WebApp {
    public WebApp() throws InterruptedException {
        // Setup template engine
        FileLoader fileLoader = new FileLoader();
        fileLoader.setPrefix("src/main/java/org/emailreportmanager/frontend/views");
        PebbleEngine engine = new PebbleEngine.Builder().cacheActive(false).loader(fileLoader).build();
        JavalinPebble.configure(engine);

        // SETUP APP
        JavalinRenderer.register(JavalinPebble.INSTANCE, ".peb");
        Javalin app = Javalin.create(config -> {
            // Static files like main.js
            config.addStaticFiles("src/main/java/org/emailreportmanager/frontend/views/js", Location.EXTERNAL);
        });

        // WebUIController(app);
        app.get("/", this::index);

        // START APP
        app.start(7070);

    }

    private void index(Context ctx) {
        Map<String, Object> model = new HashMap<>();

        /* Debug: Mock Data for Scheduler */
        List<Tool> mockToolList = new ArrayList<>();
        mockToolList.add(new Tool("MockCSV", "table"));
        mockToolList.add(new Tool("MockChart", "chart"));
        Scheduler mockScheduler = new Scheduler("Weekly500", mockToolList, null);
        model.put("scheduler", mockScheduler);
        /* END Debug */

        ctx.render("index.peb", model);
    }
}