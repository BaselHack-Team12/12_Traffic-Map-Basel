package com.baselhack17.team12;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

/**
 * //TODO write here something nicer.
 */
@Transactional
public class DAO {

    private final SessionFactory sessionFactory;

    public DAO() {
        Configuration configuration = new Configuration();
        configuration.configure();

        sessionFactory = configuration.buildSessionFactory();
    }

    int persist(Object object) {
        Session session = null;
        try {
            session = sessionFactory.openSession();
            session.beginTransaction();
            return Integer.parseInt(session.save(object).toString());
        } finally {
            session.getTransaction().commit();
            session.close();
        }
    }

    int getOrCreateStreet(String streetName, int streetCount) {
        Object streetId = isStreetExist(streetName);

        if (streetId != null) {
            return Integer.parseInt(streetId.toString());
        } else {
            streets street = new streets();
            street.setId(streetCount);
            street.setStreetName(streetName);
            return persist(street);
        }

    }

    public List<streets> getStreets() {
        Session session = null;
        try {
            session = sessionFactory.openSession();
            return session.createCriteria(streets.class)
                    .list();
        } finally {
            session.close();
        }
    }

    private Object isStreetExist(String streetName) {
        Session session = null;
        try {
            session = sessionFactory.openSession();
            return session.createCriteria(streets.class)
                    .add(Restrictions.eq("streetName", streetName))
                    .setProjection(Projections.id())
                    .uniqueResult();
        } finally {
            session.close();
        }
    }

    public Long getCarCountForStreet(int streetid) {
        Session session = null;
        try {
            session = sessionFactory.openSession();
            return (Long) session.createCriteria(cars.class)
                    .setProjection(Projections.rowCount())
                    .add(Restrictions.eq("streetId", streetid))
                    .uniqueResult();
        } finally {
            session.close();
        }
    }

    public Long getTotalCarCount() {
        Session session = null;
        try {
            session = sessionFactory.openSession();
            return (Long) session.createCriteria(cars.class)
                    .setProjection(Projections.rowCount())
                    .uniqueResult();
        } finally {
            session.close();
        }
    }

    public streets getStreet(int id) {
        Session session = null;
        try {
            session = sessionFactory.openSession();
            return (streets) session.createCriteria(streets.class)
                    .add(Restrictions.eq("id", id))
                    .uniqueResult();
        } finally {
            session.close();
        }
    }

    public streets updateStreet(int id, String speedlimit, String longitude, String latitude, String area) {
        Session session = null;
        try {
            session = sessionFactory.openSession();
            session.beginTransaction();
            streets oldstreet = (streets) session.createCriteria(streets.class)
                    .add(Restrictions.eq("id", id))
                    .uniqueResult();
            oldstreet.setSpeedlimit(Integer.parseInt(speedlimit));
            oldstreet.setLatitude(Double.parseDouble(latitude));
            oldstreet.setLongitude(Double.parseDouble(longitude));
            oldstreet.setArea(area);
            session.save(oldstreet);
            session.getTransaction().commit();
            return oldstreet;
        } finally {
            session.close();
        }
    }

}
