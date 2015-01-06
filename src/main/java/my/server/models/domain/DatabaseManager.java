/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package my.server.models.domain;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

/**
 *
 * @author AdministratorJa
 */
public class DatabaseManager {

    private final EntityManagerFactory entityManagerFactory;
    private final EntityManager entityManager;
    private static final DatabaseManager databaseManager = new DatabaseManager();

    private DatabaseManager() {
        entityManagerFactory = Persistence.createEntityManagerFactory("ClientDatabase");
        entityManager = entityManagerFactory.createEntityManager();
    }

    public static DatabaseManager getInstance() {
        return databaseManager;
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

    public List<AbstractClientModel> executeQuery(String query) {
        entityManager.getTransaction().begin();
        TypedQuery<AbstractClientModel> result = entityManager.createQuery(query, AbstractClientModel.class);
        entityManager.getTransaction().commit();
        List<AbstractClientModel> clients = (ArrayList) result;
        return clients;
    }

}
