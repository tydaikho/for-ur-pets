package com.project.forupets.repository.base;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author VyTKSE60964
 */
public class BaseDAO {
    private SessionFactory sessionFactory;

    /**
     * @param sessionFactory the sessionFactory to set
     */
    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public Session getSession() {
        return sessionFactory.openSession();
    }
}
