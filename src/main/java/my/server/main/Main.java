/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package my.server.main;

import my.server.models.domain.BusinessClientModel;
import my.server.models.DatabaseManager;
import my.server.models.domain.IndividualClientModel;

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
//        IndividualClientModel client1 = new IndividualClientModel("Jan", "Kowalski", 32);
//        IndividualClientModel client2 = new IndividualClientModel("Anna", "Kowalska", 30);
//        BusinessClientModel client3 = new BusinessClientModel("Jan", "Nowak", "Microsoft");
//        databaseManager.createClient(client1);
//        databaseManager.createClient(client2);
//        databaseManager.createClient(client3);
        IndividualClientModel indvClient = databaseManager.getClientByIndex(2L);
        System.out.println("Imie: " + indvClient.getFirstName());
        System.out.println("Nazwisko: " + indvClient.getLastName());
        System.out.println("Wiek: " + indvClient.getAge());
        BusinessClientModel busiClient = databaseManager.getClientByIndex(3L);
        System.out.println("Imie: " + busiClient.getFirstName());
        System.out.println("Nazwisko: " + busiClient.getLastName());
        System.out.println("Firma: " + busiClient.getCompanyName());
        databaseManager.close();
    }

}
