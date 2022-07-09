package com.ijeeva;

import com.ijeeva.entities.Author;
import com.ijeeva.entities.Book;
import com.ijeeva.entities.Location;
import com.ijeeva.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class CreateAuthors {

    public static void main(String[] args) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        try(Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();

            Author author = new Author("mehul", 4, 'm');
            Location location = new Location("IN", "MH", 400053);
            author.setLocation(location);

            session.save(author);

            transaction.commit();
        }
    }
}
