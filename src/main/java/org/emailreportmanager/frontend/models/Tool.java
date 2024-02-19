package org.emailreportmanager.frontend.models;

import org.emailreportmanager.entities.configurations.ElementConfiguration;

public class Tool {
    private String name;
    private String type;
    private ElementConfiguration toolConfiguration;

    public Tool(String name, String type) {
        this.name = name;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }
}
