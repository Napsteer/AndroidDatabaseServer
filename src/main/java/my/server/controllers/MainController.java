/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package my.server.controllers;

import java.util.List;
import java.util.Map;
import my.server.models.connection.Server;
import my.server.models.domain.AbstractClientModel;
import my.server.models.domain.DatabaseManager;
import my.server.views.MainView;

/**
 *
 * @author AdministratorJa
 */
public class MainController {

    private final DatabaseManager databaseManager;
    private final Server server;
    private final MainView mainView;

    public MainController(DatabaseManager databaseManager, Server server, MainView mainView) {
        this.databaseManager = databaseManager;
        this.server = server;
        this.mainView = mainView;
        init();
    }

    private void init() {
        mainView.setVisible(true);
        server.setController(this);
        server.setUpConnection();
    }

    public void log(String message) {
        mainView.logMessage(message);
    }

    public boolean addClient(AbstractClientModel client) {
        return databaseManager.createClient(client);
    }

    public List<AbstractClientModel> findClients(Map<String, Object> criteria) {
        return databaseManager.findClients(criteria);
    }
}
