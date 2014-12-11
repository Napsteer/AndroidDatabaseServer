/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package my.server.main;

import my.server.models.DatabaseManager;

/**
 *
 * @author AdministratorJa
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        DatabaseManager databaseManager = new DatabaseManager();
        databaseManager.createClient("Filip", "Cieslik", 20, null);
        databaseManager.createClient("Robert", "Cebula", 20, null);
        databaseManager.close();
    }

}
