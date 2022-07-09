package com.ijeeva;

import com.ijeeva.entities.Book;
import com.ijeeva.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class CreateBooks {

    public static void main(String[] args) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        try(Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            Book book = new Book("Python programming", 780, 500.0f);

            session.save(book);

            transaction.commit();
        }
    }
}
