/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package my.server.models;

import java.util.ArrayList;
import java.util.List;
import my.server.models.domain.AbstractClientModel;
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
    
    public void close() {
        entityManager.close();
        entityManagerFactory.close();
    }
    
    public <T extends AbstractClientModel> void createClient(T client) {
        entityManager.getTransaction().begin();
        entityManager.persist(client);
        entityManager.getTransaction().commit();
    }
    
    public List<AbstractClientModel> getClientByIndex(long index) {
        entityManager.getTransaction().begin();
        AbstractClientModel client = (AbstractClientModel) entityManager.find(AbstractClientModel.class, index);
        entityManager.getTransaction().commit();
        List<AbstractClientModel> clients = new ArrayList<>();
        clients.add(client);
        return clients;
    }
    
}
