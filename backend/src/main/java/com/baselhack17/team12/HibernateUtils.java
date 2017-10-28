package com.baselhack17.team12;

import static com.baselhack17.team12.Main.getSession;

import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

/**
 * //TODO write here something nicer.
 */
public class HibernateUtils {

    public boolean isStreetExists(Class<?> clazz, String streetName) {
        return getSession().createCriteria(clazz)
                .add(Restrictions.eq("streetname", streetName))
                .setProjection(Projections.rowCount())
                .uniqueResult() != null;
    }
}
