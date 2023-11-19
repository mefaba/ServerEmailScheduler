package org.emailreportmanager.services;


import java.sql.SQLException;
import org.h2.tools.Server;

public class H2ConsoleStarter {

    public void startServer() {
        try {
            // Start the H2 console server
            String[] consoleArgs = new String[] {"-web", "-webAllowOthers", "-webPort", "8082"};
            Server server = Server.createWebServer(consoleArgs).start();

            System.out.println("H2 Console started at http://localhost:8082");
            // To stop the console: server.stop();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}