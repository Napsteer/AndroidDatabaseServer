/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package my.server.main;

import my.server.models.BusinessClientModel;
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
        databaseManager.createClient("Jan", "Kowalski", 34, null);
        databaseManager.createClient("Ania", "Kowalska", 30, null);
        databaseManager.createClient("Jan", "Kowalski", 0, "Microsoft");
        BusinessClientModel client = databaseManager.getClientByIndex(3L);
        System.out.println("Imie: " + client.getFirstName());
        System.out.println("Nazwisko: " + client.getLastName());
        System.out.println("Wiek: " + client.getCompanyName());
        databaseManager.close();
    }

}
