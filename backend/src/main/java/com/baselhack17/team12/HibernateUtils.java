package com.baselhack17.team12;

import static com.baselhack17.team12.Main.getSession;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

/**
 * //TODO write here something nicer.
 */
public class HibernateUtils {

    public static boolean isStreetExists(Class<?> clazz, String streetName) {
        Configuration configuration = new Configuration();
        configuration.configure();

        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.openSession();

        return session.createCriteria(clazz)
                .add(Restrictions.eq("streetname", streetName))
                .setProjection(Projections.rowCount())
                .uniqueResult() != null;
    }
}
