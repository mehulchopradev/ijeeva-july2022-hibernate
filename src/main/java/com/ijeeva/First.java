package com.ijeeva;

import com.ijeeva.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class First {

    public static void main(String[] args) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        try(Session session = sessionFactory.openSession()) {
            System.out.println(session);
        }
    }
}
