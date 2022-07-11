package com.ijeeva;

import com.ijeeva.entities.Book;
import com.ijeeva.entities.PublicationHouse;
import com.ijeeva.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;

public class ExistingPublicationHouse {

    public static void main(String[] args) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        try(Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();

            PublicationHouse publicationHouse = session.get(PublicationHouse.class, 2l);
            Book book = new Book("clean code", 900, 6700f);
            book.setPublicationHouse(publicationHouse);

            session.save(book);
            transaction.commit();
        }
    }
}
