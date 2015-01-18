/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package my.server.models.domain;

import my.domain.IndividualClientModel;
import my.domain.AbstractClientModel;
import my.domain.BusinessClientModel;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author AdministratorJa
 */
public class DatabaseManager {

    private static final DatabaseManager databaseManager = new DatabaseManager();
    private final SessionFactory factory;

    private DatabaseManager() {
        factory = new AnnotationConfiguration().configure()
                .addAnnotatedClass(AbstractClientModel.class)
                .addAnnotatedClass(IndividualClientModel.class)
                .addAnnotatedClass(BusinessClientModel.class).buildSessionFactory();
    }

    public static DatabaseManager getInstance() {
        return databaseManager;
    }

    public void close() {
        factory.close();
    }

    public boolean createClient(AbstractClientModel client) {
        Session session = factory.openSession();
        try {
            session.beginTransaction();
            session.save(client);
            session.getTransaction().commit();
            return true;
        } catch (Exception ex) {
            Logger.getLogger(DatabaseManager.class.getName()).log(Level.WARNING, "Encountered exception while saving client to database.", ex);
            return false;
        } finally {
            session.close();
        }
    }

    public List<AbstractClientModel> findClients(Map<String, Object> criteria) {
        Session session = factory.openSession();
        Criteria cr;
        try {
            session.beginTransaction();
            cr = session.createCriteria(AbstractClientModel.class);
            for (Entry<String, Object> entry : criteria.entrySet()) {
                cr.add(Restrictions.eq(entry.getKey(), entry.getValue()));
            }
            return cr.list();
        } catch (Exception ex) {
            Logger.getLogger(DatabaseManager.class.getName()).log(Level.WARNING, "Encountered exception while fetching client from database.", ex);
            return null;
        } finally {
            session.close();
        }
    }

}
