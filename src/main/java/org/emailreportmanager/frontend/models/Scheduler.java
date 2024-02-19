package org.emailreportmanager.frontend.models;

import java.util.List;

public class Scheduler {
    private String name;
    private Email email;
    private List<Tool> toolList;

    public Scheduler(String name, List<Tool> toolList, Email email) {
        this.name = name;
        this.email = email;
        this.toolList = toolList;
    }

    public String getName() {
        return name;
    }

    public List<Tool> getToolList() {
        return toolList;
    }

    public Email getEmail() {
        return email;
    }

}
