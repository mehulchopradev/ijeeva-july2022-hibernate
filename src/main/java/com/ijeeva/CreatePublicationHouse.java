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

public class CreatePublicationHouse {

    public static void main(String[] args) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        try(Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();

            Calendar calendar = Calendar.getInstance();
            calendar.set(Calendar.YEAR, 2012);
            calendar.set(Calendar.MONTH, 0);
            calendar.set(Calendar.DATE, 5);
            Date date = calendar.getTime();

            PublicationHouse publicationHouse = new PublicationHouse("mehul pvt ltd", 4, date);
            Book b1 = new Book("Prog in scala", 450, 900.0f);
            b1.setPublicationHouse(publicationHouse);

            Book b2 = new Book("Ruby programming", 340, 890.0f);
            b2.setPublicationHouse(publicationHouse);

            HashSet<Book> books = new HashSet<>();
            books.add(b1);
            books.add(b2);
            publicationHouse.setBooks(books);

            session.save(publicationHouse);

            transaction.commit();
        }
    }
}
