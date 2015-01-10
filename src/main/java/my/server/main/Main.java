/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package my.server.main;

import my.server.controllers.MainController;
import my.server.models.connection.Server;
import my.server.models.domain.DatabaseManager;
import my.server.views.MainView;
/**
 *
 * @author AdministratorJa
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Server server = new Server();
        DatabaseManager databaseManager =  DatabaseManager.getInstance();
        MainView mainView = new MainView();
        MainController mainController = new MainController(databaseManager, server, mainView);
        databaseManager.close();
    }

}
