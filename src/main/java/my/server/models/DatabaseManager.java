/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package my.server.models;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author AdministratorJa
 */
public class DatabaseManager {
    
    EntityManagerFactory entityManagerFactory;
    EntityManager entityManager;

    public DatabaseManager() {
        entityManagerFactory = Persistence.createEntityManagerFactory("ClientDatabase");
        entityManager = entityManagerFactory.createEntityManager();
    }
    
    public void close(){
        entityManager.close();
        entityManagerFactory.close();
    }
    
    public void createClient(String firstName, String lastName, int age, String companyName) {
        AbstractClientModel client;
        if (companyName == null) {
            client = new IndividualClientModel(firstName, lastName, age);
        } else {
            client = new BusinessClientModel(firstName, lastName, companyName);
        }
        entityManager.getTransaction().begin();
        entityManager.persist(client);
        entityManager.getTransaction().commit();
    }
    
    
}
