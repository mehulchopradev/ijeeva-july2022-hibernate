package com.ijeeva;

import com.ijeeva.entities.Author;
import com.ijeeva.entities.Book;
import com.ijeeva.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.HashSet;
import java.util.Set;

public class BooksAndAuthors {

    public static void main(String[] args) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        try(Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();

            Author a1 = session.get(Author.class, 1l);
            Author a2 = session.get(Author.class, 2l);

            Book b1 = session.get(Book.class, 1l);
            Book b2 = session.get(Book.class, 2l);
            Book b3 = session.get(Book.class, 3l);

            Set<Book> s1 = new HashSet<>();
            s1.add(b1);
            s1.add(b3);
            a1.setBooks(s1);

            Set<Book> s2 = new HashSet<>();
            s2.add(b2);
            a2.setBooks(s2);

            transaction.commit();
        }
    }
}
